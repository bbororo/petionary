package org.pp.petionary.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {

    // 사료, 장난감, 생활용품
    FOOD(""),
    TOY("장난감"),
    LIVING("생활용품");

    private final String description;
}
