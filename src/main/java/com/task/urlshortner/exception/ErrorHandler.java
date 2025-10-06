package com.task.urlshortner.exception;

public class ErrorHandler extends RuntimeException{
    public ErrorHandler(String message){
        super(message);
    }

}
