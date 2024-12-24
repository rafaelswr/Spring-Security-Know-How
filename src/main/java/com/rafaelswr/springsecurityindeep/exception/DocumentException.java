package com.rafaelswr.springsecurityindeep.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentException extends RuntimeException{

    private String message;

    public DocumentException(String message) {
        this.message = message;
    }
}
