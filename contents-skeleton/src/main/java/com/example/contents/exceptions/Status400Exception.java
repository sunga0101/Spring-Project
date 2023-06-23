package com.example.contents.exceptions;

public abstract class Status400Exception extends RuntimeException{
    public Status400Exception(String message){
        super(message);
    }
}
/*
* 1. 400에러가 일어날 수 있는 상황 정의
*   -1. userName 중복
*   -2. email 오류
*   -3. 전화번호 오류
*   -4. 자기소개 길이 초과
*
* 2. 목표는?
*
*
* */