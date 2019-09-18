package com.dc.tenPinBowling;

public class IncorrectFileException extends Exception { 
    public IncorrectFileException(String errorMessage) {
        super(errorMessage);
    }
}