package org.pp.petionary.entity.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.exception.BadRequestException;
import org.pp.petionary.type.common.ErrorCode;

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
    private Product product;

    @Builder
    public Stock(int stockAmount, int sellAmount, Product product) {
        this.stockAmount = stockAmount;
        this.sellAmount = sellAmount;
        this.product = product;
    }

    public void decreaseStock (int amount){
        if (this.stockAmount< amount) {
            throw new BadRequestException(ErrorCode.OUT_OF_STOCK);
        }
        this.stockAmount -= amount;
        this.sellAmount += amount;
    }

    public void increaseStock(int amount) {
        this.stockAmount += amount;
        this.sellAmount -= amount;
    }
}
