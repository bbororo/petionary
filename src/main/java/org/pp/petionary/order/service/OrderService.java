package org.pp.petionary.order.service;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.order.dto.OrderProductDto;
import org.pp.petionary.order.dto.OrderRequestDto;
import org.pp.petionary.order.dto.OrderResponseDto;
import org.pp.petionary.order.dto.OrderResponseListDto;
import org.pp.petionary.user.dto.CustomUserDetails;
import org.pp.petionary.product.entity.Product;
import org.pp.petionary.product.entity.Stock;
import org.pp.petionary.order.entity.OrderProduct;
import org.pp.petionary.order.entity.Orders;
import org.pp.petionary.user.entity.Users;
import org.pp.petionary.global.exception.BadRequestException;
import org.pp.petionary.global.exception.NotFoundException;
import org.pp.petionary.product.repository.ProductRepository;
import org.pp.petionary.order.repository.OrderProductRepository;
import org.pp.petionary.order.repository.OrderRepository;
import org.pp.petionary.user.repository.UserRepository;
import org.pp.petionary.global.service.CommonService;
import org.pp.petionary.order.type.OrderStatus;
import org.pp.petionary.global.type.ErrorCode;
import org.pp.petionary.global.type.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CommonService commonService;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public CommonResponseDto<Object> postOrder(CustomUserDetails customUserDetails,OrderRequestDto orderRequestDto) {


        Users user = userRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

//        Product product = productRepository.findById(orderRequestDto.getProductId())
//                .orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));

        Product product = productRepository.findByProductWithPessimisticLock(orderRequestDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));
        logger.info("비관적락 적용");

        // 재고 확인
        Stock stock = product.getStock();
        if (stock.getStockAmount() < orderRequestDto.getCount()){
            throw new BadRequestException(ErrorCode.OUT_OF_STOCK);
        }
        // 재고 감소
        stock.decreaseStock(orderRequestDto.getCount());

        int orderPrice = product.getSale() * orderRequestDto.getCount();

        OrderProduct orderProduct = OrderProduct.builder()
                .orderProductCount(orderRequestDto.getCount())
                .orderPrice(orderPrice)
                .product(product)
                .build();

        Orders order = Orders.builder()
                .orderAddress(orderRequestDto.getAddress())
                .delivery_request(orderRequestDto.getDeliveryRequest())
                .users(user)
                .orderStatus(OrderStatus.ORDER_COMPLETE)
                .orderDate(LocalDateTime.now())
                .orderProductList(Collections.singletonList(orderProduct))
                .build();

        orderProductRepository.save(orderProduct);
        order.addOrderProduct(orderProduct);
        orderRepository.save(order);


        return commonService.successResponse(SuccessCode.ORDER_SUCCESS.getDescription(), HttpStatus.OK, null);
    }

    // 주문 취소
    @Transactional
    public CommonResponseDto<Object> cancelOrder(CustomUserDetails customUserDetails, Long orderId) {

        Users user = userRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Orders order = orderRepository.findByOrderIdAndUsers(orderId, user)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if (order.getOrderStatus() != OrderStatus.ORDER_COMPLETE) {
            throw new BadRequestException(ErrorCode.CANNOT_CANCEL_ORDER);
        }

        orderRepository.updateOrderStatus(orderId, OrderStatus.CANCELED);

        for (OrderProduct orderProduct : order.getOrderProductList()) {
            Stock stock = orderProduct.getProduct().getStock();
            stock.increaseStock(orderProduct.getOrderProductCount());
        }

        return commonService.successResponse(SuccessCode.ORDER_CANCEL_SUCCESS.getDescription(), HttpStatus.OK, null);
    }

    // 반품하기
    @Transactional
    public CommonResponseDto<Object> refundOrder(CustomUserDetails customUserDetails, Long orderId) {

        Users user = userRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Orders order = orderRepository.findByOrderIdAndUsers(orderId, user)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        // 배송 완료 후 D+1일까지 반품 가능
        if (order.getOrderStatus() != OrderStatus.RECEIVING) {
            throw new BadRequestException(ErrorCode.CANNOT_REFUND_ORDER);
        }

        // 배송 완료 후 D+1일까지 반품 가능
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deliveryCompleteDate = order.getOrderDate().plus(3, ChronoUnit.DAYS);
        if (now.isAfter(deliveryCompleteDate)) {
            throw new BadRequestException(ErrorCode.REFUND_PERIOD_EXPIRATION);
        }

        // 주문 상태를 반품 신청으로 변경
        orderRepository.updateOrderStatus(orderId, OrderStatus.REQUEST_REFUND);

        return commonService.successResponse(SuccessCode.ORDER_REFUND_REQUEST.getDescription(), HttpStatus.OK, null);
    }

    // 반품 처리 스케줄러
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    @Scheduled(cron = "0 * * * * ?") //매 1분마다 실행
    @Transactional
    public void processRefunds() {
        List<Orders> orderList = orderRepository.findAllByOrderStatusAndOrderDateBefore(OrderStatus.REQUEST_REFUND, LocalDateTime.now().minusDays(1));

        for (Orders order : orderList) {
            for (OrderProduct orderProduct : order.getOrderProductList()) {
                Stock stock = orderProduct.getProduct().getStock();
                stock.increaseStock(orderProduct.getOrderProductCount());

            }
            orderRepository.updateOrderStatus(order.getOrderId(), OrderStatus.REFUNDED);

        }
    }

    // 주문상태 업데이트
    // 주문 D+1 배송중
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    @Scheduled(cron = "0 * * * * ?") //매 1분마다 실행
    @Transactional
    public void updateOrderStatusToShipping() {
        List<Orders> ordersToShip = orderRepository.findAllByOrderStatusAndOrderDateBefore(OrderStatus.ORDER_COMPLETE, LocalDateTime.now().minusDays(1));

        for (Orders order : ordersToShip) {
            orderRepository.updateOrderStatus(order.getOrderId(), OrderStatus.DELIVERING);
            logger.info("Order ID {} status updated to DELIVERING", order.getOrderId());
        }
    }

    // 주문 D+2 배송완료
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    @Scheduled(cron = "0 * * * * ?") //매 1분마다 실행
    @Transactional
    public void updateOrderStatusToDelivered() {
        List<Orders> ordersToDeliver = orderRepository.findAllByOrderStatusAndOrderDateBefore(OrderStatus.DELIVERING, LocalDateTime.now().minusDays(2));

        for (Orders order : ordersToDeliver) {
            orderRepository.updateOrderStatus(order.getOrderId(), OrderStatus.RECEIVING);
            logger.info("Order ID {} status updated to RECEIVING", order.getOrderId());
        }
    }


    // 유저의 주문 내역
    @Transactional
    public CommonResponseDto<Object> getOrders(CustomUserDetails customUserDetails) {

        Users user = userRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        List<Orders> ordersList = orderRepository.findOrdersByUsersId(user.getUserId());
        List<OrderResponseDto> orderResponseDtoList = ordersList.stream()
                .map(order -> OrderResponseDto.builder()
                        .orderId(order.getOrderId())
                        .orderStatus(order.getOrderStatus().getDescription())
                        .orderAddress(order.getOrderAddress())
                        .delivery_request(order.getDelivery_request())
                        .orderDate(order.getOrderDate())
                        .totalPrice(order.totalPrice())
                        .orderProductList(order.getOrderProductList().stream()
                                .map(orderProduct -> OrderProductDto.builder()
                                        .orderProductId(orderProduct.getOrderProductId())
                                        .productName(orderProduct.getProduct().getName())
                                        .count(orderProduct.getOrderProductCount())
                                        .price(orderProduct.getOrderPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        OrderResponseListDto orderResponseListDto = OrderResponseListDto.builder()
                .orderResponseListDto(orderResponseDtoList)
                .build();


        return commonService.successResponse(SuccessCode.ORDER_STATUS_INQUIRY_SUCCESS.getDescription(), HttpStatus.OK, orderResponseListDto);
    }



}
