package com.example.freshermanagement.validator.impl;

import com.example.freshermanagement.entity.Manager;
import com.example.freshermanagement.exception.EmailException;
import com.example.freshermanagement.exception.PhoneException;
import com.example.freshermanagement.exception.UsernameException;
import com.example.freshermanagement.exception.ValidationException;
import com.example.freshermanagement.repository.ManagerRepository;
import com.example.freshermanagement.repository.UserRepository;
import com.example.freshermanagement.validator.EmailValidator;
import com.example.freshermanagement.validator.ManagerValidator;
import com.example.freshermanagement.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ManagerValidatorImpl implements ManagerValidator {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(managerRepository.findByEmail(email) != null){
            log.error("Email is already taken!");
            throw new EmailException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(managerRepository.findByPhone(phone) != null){
            log.error("Phone is already taken!");
            throw new PhoneException("Phone is already taken!");
        }
    }

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
    public void validateCreate(Manager manager) {
        validateUsername(manager.getUsername());
        validateEmail(manager.getEmail());
        validatePhone(manager.getPhone());
    }
}
