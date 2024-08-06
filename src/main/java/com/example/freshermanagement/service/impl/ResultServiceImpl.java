package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Result;
import com.example.freshermanagement.repository.ResultRepository;
import com.example.freshermanagement.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public boolean save(Result result) {
        resultRepository.save(result);
        return true;
    }
}
