package org.pp.petionary.global.exception;

import org.pp.petionary.global.type.ErrorCode;

public class NotFoundException extends RuntimeException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
    }
}
