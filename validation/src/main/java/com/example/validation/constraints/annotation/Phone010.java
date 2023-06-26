package com.example.validation.constraints.annotation;

import com.example.validation.constraints.Phone010Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션을 어디에 적용할 것인지 (선택)
@Target(ElementType.FIELD)
// 어노테이션이 언제까지 유지될 것인지
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Phone010Validator.class)
public @interface Phone010 {
        // Annotation Element
        String message() default "010으로 시작하지 않음";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


