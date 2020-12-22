package com.github.daedalus.errorHandler;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class ErrorHandlerException extends RuntimeException{
    private final String errorMessage;
    private final HttpStatus errorCode;

    public ErrorHandlerException(String errorMessage,
                                 HttpStatus errorCode,
                                 String logMessage){
        super(logMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
