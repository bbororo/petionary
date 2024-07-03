package org.pp.petionary.controller.order;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.dto.ExampleDto;
import org.pp.petionary.dto.common.CommonResponseDto;
import org.pp.petionary.dto.common.ResultDto;
import org.pp.petionary.dto.order.OrderRequestDto;
import org.pp.petionary.dto.order.OrderResponseDto;
import org.pp.petionary.dto.order.OrderResponseListDto;
import org.pp.petionary.dto.user.CustomUserDetails;
import org.pp.petionary.entity.order.Orders;
import org.pp.petionary.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petionary/order")
public class OrderController {

    private final OrderService orderService;

    // 주문 하기
    @PostMapping
    public ResponseEntity<ResultDto<Void>> postOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                       @RequestBody OrderRequestDto orderRequestDto){
        CommonResponseDto<Object> commonResponseDto = orderService.postOrder(customUserDetails, orderRequestDto);
        ResultDto<Void> resultDto = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(resultDto);
    }

    // 주문 취소
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<ResultDto<Void>> cancelOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                       @PathVariable Long orderId) {
        CommonResponseDto<Object> commonResponseDto = orderService.cancelOrder(customUserDetails, orderId);
        ResultDto<Void> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);

    }

    // 반품 하기
    @PutMapping("/refund/{orderId}")
    public ResponseEntity<ResultDto<Void>> refundOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                       @PathVariable Long orderId) {
        CommonResponseDto<Object> commonResponseDto = orderService.refundOrder(customUserDetails, orderId);
        ResultDto<Void> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);

    }

    // 유저의 주문 조회
    @GetMapping
    public ResponseEntity<ResultDto<OrderResponseListDto>> getOrders(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        CommonResponseDto<Object> commonResponseDto = orderService.getOrders(customUserDetails);
        ResultDto<OrderResponseListDto> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());
        result.setData((OrderResponseListDto) commonResponseDto.getData());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);

    }

}
