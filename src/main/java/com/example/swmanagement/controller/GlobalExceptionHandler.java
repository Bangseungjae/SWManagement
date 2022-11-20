package com.example.swmanagement.controller;

import com.example.swmanagement.exception.ErrorResponse;
import com.example.swmanagement.exception.MissMatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissMatchException.class)
    public ErrorResponse handlerMissMatchException(MissMatchException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handlerIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(ex.getMessage(), 400);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage(), 400);
    }

    @ExceptionHandler(ServerException.class)
    public ErrorResponse handlerServerException(ServerException ex) {
        return new ErrorResponse(ex.getMessage(), 500);
    }
}
