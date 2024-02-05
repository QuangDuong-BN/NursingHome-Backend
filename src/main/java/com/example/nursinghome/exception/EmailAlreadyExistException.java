package com.example.nursinghome.exception;

public class EmailAlreadyExistException extends  RuntimeException{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
