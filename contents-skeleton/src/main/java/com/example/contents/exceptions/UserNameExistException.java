package com.example.contents.exceptions;

public class UserNameExistException extends Status400Exception {

    public UserNameExistException(String message) {
        super("Username not uniqe");
    }
}
