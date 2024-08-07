package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.*;
import com.example.freshermanagement.exception.MaxTestException;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.FresherRepository;
import com.example.freshermanagement.service.*;
import com.example.freshermanagement.validator.FresherValidator;
import com.example.freshermanagement.validator.KeywordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FresherServiceImpl implements FresherService {
    public static final int MAX_TESTS = 3;

    private final LanguageService languageService;
    private final RoleService roleService;
    private final FresherRepository fresherRepository;
    private final PasswordEncoder passwordEncoder;
    private final FresherValidator fresherValidator;
    private final RoleService roleCheckService;
    private final TestService testService;
    private final ResultService resultService;
    private final KeywordValidator keywordValidator;

    @Override
    @CacheEvict(value = "freshers", allEntries = true)
    public boolean save(Fresher user) {
        fresherValidator.validateCreate(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLanguage(languageService.findById(user.getLanguage().getId()));

        Role role = roleService.findByName(RoleName.FRESHER);
        user.setRoles(Collections.singletonList(role));

        fresherRepository.save(user);

        return true;
    }

    @Override
    @CacheEvict(value = "freshers", allEntries = true)
    public boolean deleteById(Long id) {
        Fresher fresher = getActiveUserById(id);
        fresher.setStatus(false);
        fresherRepository.save(fresher);
        return true;
    }

    @Override
    @CacheEvict(value = "freshers", allEntries = true)
    public boolean updateById(Long id, Fresher updateFresher) {
        Fresher fresher = getActiveUserById(id);

        fresherValidator.validateUpdate(updateFresher, fresher);

        fresher.setFirstname(updateFresher.getFirstname());
        fresher.setLastname(updateFresher.getLastname());
        fresher.setDob(updateFresher.getDob());
        fresher.setEmail(updateFresher.getEmail());
        fresher.setPhone(updateFresher.getPhone());
        fresher.setLanguage(languageService.findById(updateFresher.getLanguage().getId()));

        fresherRepository.save(fresher);

        return true;
    }

    @Override
    @Transactional
    public boolean scoringForFresher(Long id, Result result) {
        Fresher fresher = getActiveUserById(id);
        if(fresher.getResults().size() >= MAX_TESTS){
            log.error("The number of fresher tests has been maxed");
            throw new MaxTestException("The number of fresher tests has been maxed");
        }

        Test test = testService.findById(result.getTest().getId());

        result.setFresher(fresher);
        result.setTest(test);

        resultService.save(result);

        return true;
    }

    @Override
    @Cacheable("freshers")
    public List<Fresher> findAllWithAdminOrManager(String usernameAdminOrManager) {
        if(roleCheckService.isAdmin()){
            return fresherRepository.findAll();
        }
        if(roleCheckService.isManager()){
            return fresherRepository.findByManagerUsername(usernameAdminOrManager);
        }
        return null;
    }

    @Override
    public List<Fresher> findAllByNameWithAdminOrManager(String usernameAdminOrManager, String keyword) {
        keywordValidator.validate(keyword);

        List<Fresher> listAll = findAllWithAdminOrManager(usernameAdminOrManager);

        return listAll.stream()
                .filter(fresher ->
                        fresher.getFirstname().toLowerCase().contains(keyword.toLowerCase()) ||
                        fresher.getLastname().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<Fresher> findAllByEmailWithAdminOrManager(String usernameAdminOrManager, String keyword) {
        keywordValidator.validate(keyword);

        List<Fresher> listAll = findAllWithAdminOrManager(usernameAdminOrManager);

        return listAll.stream()
                .filter(fresher -> fresher.getEmail().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<Fresher> findAllByLanguageWithAdminOrManager(String usernameAdminOrManager, String keyword) {
        keywordValidator.validate(keyword);

        List<Fresher> listAll = findAllWithAdminOrManager(usernameAdminOrManager);

        return listAll.stream()
                .filter(fresher -> fresher.getLanguage().getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    @Cacheable(value = "freshers", key = "#id")
    public Fresher getActiveUserById(Long id) {
        return fresherRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> {
                    log.error("Fresher not found with id: " + id);
                    return new ResourceNotFoundException("Fresher not found with id: " + id);
                });
    }
}
