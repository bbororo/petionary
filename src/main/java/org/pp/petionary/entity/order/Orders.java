package org.pp.petionary.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.type.OrderStatus;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "order_address")
    private String orderAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "refund_reason")
    private String refundReason;

    @Column(name = "delivery_request")
    private String delivery_request;

}
