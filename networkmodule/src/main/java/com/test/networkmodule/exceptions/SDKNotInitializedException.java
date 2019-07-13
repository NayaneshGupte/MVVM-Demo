package com.test.networkmodule.exceptions;

public class SDKNotInitializedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Network SDK is not initialized, Please call the initialize method of SDK Manager";
    }
}
