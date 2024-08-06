package com.example.freshermanagement.validator.impl;

import com.example.freshermanagement.exception.ValidationException;
import com.example.freshermanagement.validator.PhoneValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
public class PhoneValidatorImpl implements PhoneValidator {

    private static final String PHONE_REGEX = "^\\d{9,}$";

    private static final Pattern pattern = Pattern.compile(PHONE_REGEX);

    @Override
    public void validate(String phone) {
        if (!pattern.matcher(phone).matches()) {
            log.error("Phone number is not valid.");
            throw new ValidationException("Phone number is not valid.");
        }


    }
}
