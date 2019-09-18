package com.dc.tenPinBowling;

public class IncorrectThrowException extends RuntimeException { 
    public IncorrectThrowException(String errorMessage) {
        super(errorMessage);
    }
}