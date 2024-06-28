package org.pp.petionary.exception;


import org.pp.petionary.type.common.ErrorCode;

public class BadRequestException extends RuntimeException{
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getDescription());
    }
}
