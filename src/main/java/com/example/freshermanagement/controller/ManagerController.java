package com.example.freshermanagement.controller;

import com.example.freshermanagement.entity.Manager;
import com.example.freshermanagement.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Manager manager){
        managerService.save(manager);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("")
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok(managerService.findAll());
    }

}
