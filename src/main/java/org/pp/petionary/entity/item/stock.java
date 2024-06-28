package org.pp.petionary.entity.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_amount")
    private int stockAmount;

    @Column(name = "sell_amount")
    private int sellAmount;
}
