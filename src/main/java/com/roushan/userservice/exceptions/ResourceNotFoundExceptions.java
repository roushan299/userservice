package com.roushan.userservice.exceptions;


public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions() {
        super("Resource not found on server");
    }

    public ResourceNotFoundExceptions(String message) {
        super(message);
    }
}
