package com.dc.tenPinBowling;

public class IncorrectThrowException extends Exception { 
    public IncorrectThrowException(String errorMessage) {
        super(errorMessage);
    }
}