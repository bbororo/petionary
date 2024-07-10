package org.pp.petionary.order.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pp.petionary.global.entity.BaseTime;
import org.pp.petionary.product.entity.Product;

@Entity
@Getter
@NoArgsConstructor
public class OrderProduct extends BaseTime  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long orderProductId;

    @Column(name = "order_product_count")
    private int orderProductCount;

    @Column(name = "order_price")
    private int orderPrice;

    @Setter
    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;


    @Builder
    public OrderProduct(int orderProductCount, int orderPrice, Orders orders, Product product) {
        this.orderProductCount = orderProductCount;
        this.orderPrice = orderPrice;
        this.orders = orders;
        this.product = product;
    }


}
