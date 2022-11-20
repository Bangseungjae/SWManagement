package com.example.swmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse extends RuntimeException{
    String message;
    Integer code;
}
