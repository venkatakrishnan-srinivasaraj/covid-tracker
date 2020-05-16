package com.altimetrik.playground.covidtracker.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidState {
    String message() default "Invalid state code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
