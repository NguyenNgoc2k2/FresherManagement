package com.example.freshermanagement.validator.impl;

import com.example.freshermanagement.exception.ValidationException;
import com.example.freshermanagement.validator.KeywordValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeywordValidatorImpl implements KeywordValidator {
    @Override
    public void validate(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            log.error("Keyword must not be empty");
            throw new ValidationException("Keyword must not be empty");
        }
    }
}
