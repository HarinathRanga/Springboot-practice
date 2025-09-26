package com.harinath.Homework2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null){
            return false;
        }
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^0-9a-zA-Z]).{10,}$";
        return password.matches(passwordRegex);
    }
}
