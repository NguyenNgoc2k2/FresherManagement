package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Language;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.LanguageRepository;
import com.example.freshermanagement.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Language not found with id: " + id);
                    return new ResourceNotFoundException("Language not found with id: " + id);
                });
    }
}
