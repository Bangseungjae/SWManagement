package com.example.swmanagement.exception;

import lombok.Getter;

@Getter
public class MissMatchException extends RuntimeException{

    String message;
    Integer code;

    public MissMatchException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
