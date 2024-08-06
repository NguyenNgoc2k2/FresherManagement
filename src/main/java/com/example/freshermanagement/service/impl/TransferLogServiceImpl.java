package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.TransferLog;
import com.example.freshermanagement.repository.TransferLogRepository;
import com.example.freshermanagement.service.TransferLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferLogServiceImpl implements TransferLogService {

    private final TransferLogRepository transferLogRepository;

    @Override
    public boolean save(TransferLog transferLog) {
        transferLogRepository.save(transferLog);
        return true;
    }
}
