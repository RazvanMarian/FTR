package com.unibuc.FTR.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.unibuc.FTR.constants.Constants.SEASON_INCORRECT_FORMAT;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SeasonFormatValidation.class})
public @interface SeasonFormat {

    String message() default SEASON_INCORRECT_FORMAT;

    //represents group of constraints
    Class<?>[] groups() default {};

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
