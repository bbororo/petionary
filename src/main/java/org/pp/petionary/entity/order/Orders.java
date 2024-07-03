package org.pp.petionary.entity.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pp.petionary.entity.basetime.BaseTime;
import org.pp.petionary.entity.user.Users;
import org.pp.petionary.type.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Orders extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

//    @Column(name = "total_price")
//    private int totalPrice;

    @Column(name = "order_address")
    private String orderAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

//    @Column(name = "refund_reason")
//    private String refundReason;

    @Column(name = "delivery_request")
    private String delivery_request;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Builder
    public Orders(String orderAddress, OrderStatus orderStatus, LocalDateTime orderDate, /*String refundReason, */String delivery_request, Users users, List<OrderItem> orderItemList) {
        this.orderAddress = orderAddress;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
//        this.refundReason = refundReason;
        this.delivery_request = delivery_request;
        this.users = users;
        this.orderItemList = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrders(this); // 양방향 연관관계 설정
    }

    public int totalPrice() {
        return orderItemList.stream()
                .mapToInt(OrderItem::getOrderPrice)
                .sum();
    }

}
