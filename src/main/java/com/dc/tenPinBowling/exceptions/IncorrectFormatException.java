package com.dc.tenPinBowling;

public class IncorrectFormatException extends Exception { 
    public IncorrectFormatException(String errorMessage) {
        super(errorMessage);
    }
}