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
@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A data deve estar no formato yyyy-MM-dd.")
public @interface DataFormat {
    String message() default "A data deve estar no formato yyyy-MM-dd.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
