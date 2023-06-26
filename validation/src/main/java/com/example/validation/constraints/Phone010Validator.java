package com.example.validation.constraints;


import com.example.validation.constraints.annotation.Phone010;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class Phone010Validator
        implements ConstraintValidator<Phone010, String> {


    private final Set<String> phoneList;
    public Phone010Validator() {
        this.phoneList = new HashSet<>();
        this.phoneList.add("010");
        this.phoneList.add("010-");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 유효한 값일 때 true를 반환
        // 유효하지 않을 값일 때 false를 반환

        // 조건 010- , (010)
        boolean withDash = value.startsWith("010-");
        boolean withPar = value.startsWith("(010)");
        return withPar || withDash ;
    }
}
