package com.test.networkmodule.exceptions;

public class ApiException extends RuntimeException {
    private int exceptionCode = -1;

    public ApiException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
