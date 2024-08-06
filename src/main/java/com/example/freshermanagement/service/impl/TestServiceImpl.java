package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Test;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.TestRepository;
import com.example.freshermanagement.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public Test findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Test not found with id: " + id);
                    return new ResourceNotFoundException("Test not found with id: " + id);
                });
    }
}
