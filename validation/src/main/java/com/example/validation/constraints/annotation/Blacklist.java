package com.example.validation.constraints.annotation;

import com.example.validation.constraints.BlacklistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BlacklistValidator.class)
public @interface Blacklist {
    // Annotation Element
    String message() default "username in blacklist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 추가 element 작성
    String[] blacklist() default {};
}
