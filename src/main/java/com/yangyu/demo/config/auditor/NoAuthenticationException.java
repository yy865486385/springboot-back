package com.yangyu.demo.config.auditor;

public class NoAuthenticationException extends NullPointerException{

    public NoAuthenticationException(String message) {
        super(message);
    }

}