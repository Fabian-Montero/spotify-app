package com.spotifyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(){

    }
    //Constructor with message

    public ResourceNotFoundException(String message){
        super(message);
    }
    //Constructor with message and cause
    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
