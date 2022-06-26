package com.customer.exception;

import java.util.Map;

public abstract class BaseException extends RuntimeException {

    private Map<String, Object> mapError;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Map mapError) {
        super(message);
        this.mapError = mapError;
    }

    public Map<String, Object> getMapError() {
        return mapError;
    }

}