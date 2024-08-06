package com.example.freshermanagement.service;

import com.example.freshermanagement.dto.CenterRequest;
import com.example.freshermanagement.entity.Center;
import com.example.freshermanagement.entity.Course;

import java.util.List;

public interface CenterService {
    List<Center> getAll();
    Center create(CenterRequest centerRequest);
    boolean deleteById(Long id);
    boolean updateById(Long id, CenterRequest centerRequest);
    boolean addCourseById(Long id, Course course);
    boolean assignFresherToCenter(Long id, Long fresherId, Long courseId);
    boolean merge(Long idCenter1, Long idCenter2, CenterRequest newCenter);
    Center getActiveCenterById(Long id);
    boolean changeManager(Center center, Long managerId);
}
