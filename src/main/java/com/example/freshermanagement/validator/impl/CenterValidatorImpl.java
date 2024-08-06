package com.example.freshermanagement.validator.impl;

import com.example.freshermanagement.dto.CenterRequest;
import com.example.freshermanagement.entity.Center;
import com.example.freshermanagement.exception.CenterNameException;
import com.example.freshermanagement.exception.EmailException;
import com.example.freshermanagement.exception.PhoneException;
import com.example.freshermanagement.exception.ValidationException;
import com.example.freshermanagement.repository.CenterRepository;
import com.example.freshermanagement.validator.CenterValidator;
import com.example.freshermanagement.validator.EmailValidator;
import com.example.freshermanagement.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CenterValidatorImpl implements CenterValidator {

    private final CenterRepository centerRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateName(String name) {
        if(name == null || name.isEmpty()){
            log.error("Center name cannot be null or empty");
            throw new ValidationException("Center name cannot be null or empty");
        }

        if(centerRepository.findByName(name) != null){
            log.error("Name is already taken!");
            throw new CenterNameException("Name is already taken!");
        }
    }

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(centerRepository.findByEmail(email) != null){
            log.error("Email is already taken!");
            throw new EmailException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(centerRepository.findByPhone(phone) != null){
            log.error("Phone is already taken!");
            throw new PhoneException("Phone is already taken!");
        }
    }

    @Override
    public void validateCreate(CenterRequest createCenter) {
        validateName(createCenter.getName());
        validateEmail(createCenter.getEmail());
        validatePhone(createCenter.getPhone());
    }

    @Override
    public void validateUpdate(CenterRequest updateCenter, Center currentCenter) {
        if(!updateCenter.getName().equalsIgnoreCase(currentCenter.getName())){
            validateName(updateCenter.getName());
        }

        if(!updateCenter.getEmail().equalsIgnoreCase(currentCenter.getEmail())){
            validateEmail(updateCenter.getEmail());
        }

        if(!updateCenter.getPhone().equalsIgnoreCase(currentCenter.getPhone())){
            validatePhone(updateCenter.getPhone());
        }
    }
}
