package org.pp.petionary.exception;

import org.pp.petionary.type.common.ErrorCode;

public class NotFoundException extends RuntimeException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
    }
}
