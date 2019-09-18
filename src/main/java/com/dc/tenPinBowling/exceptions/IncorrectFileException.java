package com.dc.tenPinBowling;

public class IncorrectFileException extends RuntimeException { 
    public IncorrectFileException(String errorMessage) {
        super(errorMessage);
    }
}