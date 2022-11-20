package com.example.swmanagement.controller;

import com.example.swmanagement.exception.ErrorResponse;
import com.example.swmanagement.exception.MissMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerMissMatchException(MissMatchException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorResponse(ex.getMessage(), 400);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerRuntimeException(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage(), 400);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerServerException(ServerException ex) {
        return new ErrorResponse(ex.getMessage(), 500);
    }
}
