package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Area;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.AreaRepository;
import com.example.freshermanagement.service.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Override
    public Area getActiveById(Long id) {
        return areaRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> {
                    log.error("Area not found with id: " + id);
                    return new ResourceNotFoundException("Area not found with id: " + id);
                });
    }
}
