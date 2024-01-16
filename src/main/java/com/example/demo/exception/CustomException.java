package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{
    private String message;
    private String is8n;
    public CustomException(String message){
        this.message = message;
    }
}
