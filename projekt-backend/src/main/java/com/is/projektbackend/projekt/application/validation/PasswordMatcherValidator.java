package com.is.projektbackend.projekt.application.validation;

import com.is.projektbackend.projekt.application.dto.CreateMemberDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        CreateMemberDto memberDTO = (CreateMemberDto) value;
        if (memberDTO.getPassword() == null || memberDTO.getRepeatedPassword() == null) {
            return false;
        }
        return memberDTO.getPassword().equals(memberDTO.getRepeatedPassword());
    }

    @Override
    public void initialize(PasswordMatcher constraintAnnotation) {

    }

}
