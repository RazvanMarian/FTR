package com.unibuc.FTR.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeasonFormatValidation implements ConstraintValidator<SeasonFormat, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return false;

        var years = value.split("/");

        if(Integer.parseInt(years[0]) + 1 != Integer.parseInt(years[1]))
            return false;

        return value.matches("(?:20[0-3][0-9]|2040)/(?:20[0-3][0-9]|2040+1)");
    }
}