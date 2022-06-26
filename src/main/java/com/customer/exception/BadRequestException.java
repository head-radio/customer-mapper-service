package com.customer.exception;

import java.util.Map;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Map mapError) {
        super(message, mapError);
    }
}