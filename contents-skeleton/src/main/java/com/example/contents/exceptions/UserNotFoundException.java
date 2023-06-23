package com.example.contents.exceptions;

public class UserNotFoundException extends Status404Exception{
    public UserNotFoundException() {
        super("Username not found");
    }
}
