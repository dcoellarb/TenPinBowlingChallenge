package com.dc.tenPinBowling;

public class IncorrectFormatException extends RuntimeException { 
    public IncorrectFormatException(String errorMessage) {
        super(errorMessage);
    }
}