package org.pp.petionary.global.exception;


import org.pp.petionary.global.type.ErrorCode;

public class BadRequestException extends RuntimeException{
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getDescription());
    }
}
