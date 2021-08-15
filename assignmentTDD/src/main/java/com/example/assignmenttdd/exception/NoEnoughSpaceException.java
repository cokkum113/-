package com.example.assignmenttdd.exception;

public class NoEnoughSpaceException extends RuntimeException
{
    public NoEnoughSpaceException(String message)
    {
        super(message);
    }
}
