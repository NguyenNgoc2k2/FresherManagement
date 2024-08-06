package com.example.freshermanagement.validator.impl;

import com.example.freshermanagement.entity.Fresher;
import com.example.freshermanagement.exception.*;
import com.example.freshermanagement.repository.FresherRepository;
import com.example.freshermanagement.repository.UserRepository;
import com.example.freshermanagement.validator.EmailValidator;
import com.example.freshermanagement.validator.FresherValidator;
import com.example.freshermanagement.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FresherValidatorImpl implements FresherValidator {

    private final FresherRepository fresherRepository;
    private final UserRepository userRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateUsername(String username) {
        if(username == null || username.isEmpty()){
            log.error("Username cannot be null or empty");
            throw new ValidationException("Username cannot be null or empty");
        }

        if(userRepository.findByUsername(username) != null){
            log.error("Username is already taken!");
            throw new UsernameException("Username is already taken!");
        }
    }

    @Override
    public void validateCode(String code) {
        if(code == null || code.isEmpty()){
            log.error("Fresher Code cannot be null or empty");
            throw new ValidationException("Fresher Code cannot be null or empty");
        }

        if(fresherRepository.findByCode(code) != null){
            log.error("Code is already taken!");
            throw new FresherCodeAlreadyExistsException("Code is already taken!");
        }
    }

    @Override
    public void validateCreate(Fresher createFresher) {
        validateCode(createFresher.getCode());
        validateUsername(createFresher.getUsername());
        validateEmail(createFresher.getEmail());
        validatePhone(createFresher.getPhone());
    }

    @Override
    public void validateUpdate(Fresher updateFresher, Fresher currentFresher) {
        if(!updateFresher.getEmail().equalsIgnoreCase(currentFresher.getEmail())){
            validateEmail(updateFresher.getEmail());
        }

        if(!updateFresher.getPhone().equalsIgnoreCase(currentFresher.getPhone())){
            validatePhone(updateFresher.getPhone());
        }
    }

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(fresherRepository.findByEmail(email) != null){
            throw new EmailException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(fresherRepository.findByPhone(phone) != null){
            throw new PhoneException("Phone is already taken!");
        }
    }
}
