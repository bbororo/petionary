package org.pp.petionary.type.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS("success"),
    FAIL("fail");

    private final String description;
}
