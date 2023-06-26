package com.example.validation.constraints.annotation;

import com.example.validation.constraints.EmailWhilelistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 말 그대로 어노테이션을 적용할 타겟 (어디에)
@Retention(RetentionPolicy.RUNTIME) // 계속 유지되어야 함 (언제)
@Constraint(validatedBy = EmailWhilelistValidator.class) // 이걸로 유효성 검증한다
public @interface EmailWhitelist {
    // 어노테이션에서는 메소드 같은 것을 element라고 한다
    String message() default "email not in whitelist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
