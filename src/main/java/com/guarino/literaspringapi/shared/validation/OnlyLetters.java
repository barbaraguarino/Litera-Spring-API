package com.guarino.literaspringapi.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "O campo deve conter apenas letras e espaços.")
public @interface OnlyLetters {
    String message() default "O campo deve conter apenas letras e espaços.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
