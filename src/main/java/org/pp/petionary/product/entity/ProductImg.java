package org.pp.petionary.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.global.entity.BaseTime;

@Entity
@NoArgsConstructor
@Getter
public class ProductImg extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public ProductImg(String imgUrl, Product product) {
        this.imgUrl = imgUrl;
        this.product = product;
    }
}
