package org.pp.petionary.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.global.entity.BaseTime;
import org.pp.petionary.product.type.ProductStatus;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private String content;

    private int price;

    private int sale;

//    @Enumerated(EnumType.STRING)
//    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductImg> productImgList;

    @OneToOne(mappedBy = "product")
    private Stock stock;

    @Builder
    public Product(String name, String content, int price, int sale, ProductStatus productStatus, List<ProductImg> productImgList, Stock stock) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.sale = sale;
        this.productStatus = productStatus;
        this.productImgList = productImgList;
        this.stock = stock;
    }
}
