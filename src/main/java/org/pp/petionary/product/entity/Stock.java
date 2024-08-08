package org.pp.petionary.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.global.exception.BadRequestException;
import org.pp.petionary.global.type.ErrorCode;

@Entity
@NoArgsConstructor
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_amount")
    private int stockAmount;

    @Column(name = "sell_amount")
    private int sellAmount;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Builder
    public Stock(int stockAmount, int sellAmount, Product product) {
        this.stockAmount = stockAmount;
        this.sellAmount = sellAmount;
        this.product = product;
    }

    public void decreaseStock (int amount, Product product){
        if (this.stockAmount< amount) {
            throw new BadRequestException(ErrorCode.OUT_OF_STOCK);
        }
        this.stockAmount -= amount;
        this.sellAmount += amount;
        this.product = product;
    }

    public void increaseStock(int amount) {
        this.stockAmount += amount;
        this.sellAmount -= amount;
    }
}
