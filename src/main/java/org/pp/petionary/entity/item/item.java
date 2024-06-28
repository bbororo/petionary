package org.pp.petionary.entity.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.type.ItemStatus;

@Entity
@Getter
@NoArgsConstructor
public class item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    private String name;

    private String content;

    private int price;

    private int sale;

//    @Enumerated(EnumType.STRING)
//    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;


}
