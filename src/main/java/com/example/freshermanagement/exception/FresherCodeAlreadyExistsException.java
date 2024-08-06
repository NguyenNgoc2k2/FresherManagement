package com.example.freshermanagement.exception;

public class FresherCodeAlreadyExistsException extends RuntimeException{
    public FresherCodeAlreadyExistsException(String message){
        super(message);
    }
}
