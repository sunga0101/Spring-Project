package com.example.validation.constraints;

import com.example.validation.constraints.annotation.EmailWhitelist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class EmailWhilelistValidator implements
        // 사용자 지정 유효성 검사를 위해서 구현을 해야하는 인터페이스
        ConstraintValidator<EmailWhitelist, String> {

    private final Set<String> whiteList;
    public EmailWhilelistValidator(){
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 유효한 값일 때 true를 반환
        // 유효하지 않을 값일 때 false를 반환
        String[] split = value.split("@");
        String domain = split[split.length-1];

        return whiteList.contains(domain);
    }
}
