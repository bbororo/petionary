package org.pp.petionary.entity.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pp.petionary.entity.basetime.BaseTime;
import org.pp.petionary.entity.item.Product;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseTime  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_cnt")
    private int orderCnt;

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
    public OrderItem(int orderCnt, int orderPrice, Orders orders, Product product) {
        this.orderCnt = orderCnt;
        this.orderPrice = orderPrice;
        this.orders = orders;
        this.product = product;
    }


}
