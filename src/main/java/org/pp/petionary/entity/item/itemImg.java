package org.pp.petionary.entity.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class itemImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    private String imgUrl;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
