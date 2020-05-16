package com.altimetrik.playground.covidtracker.validation;

import com.altimetrik.playground.covidtracker.service.StateService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class StateValidator implements ConstraintValidator<ValidState, String> {

    private final StateService stateService;

    @Override
    public boolean isValid(String stateCode, ConstraintValidatorContext constraintValidatorContext) {
        return stateService.isValidStateCode(stateCode);
    }

}
