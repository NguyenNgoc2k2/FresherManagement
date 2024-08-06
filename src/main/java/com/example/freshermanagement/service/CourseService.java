package com.example.freshermanagement.service;

import com.example.freshermanagement.entity.Course;

public interface CourseService {
    boolean save(Course course);
    Course getActiveCourseById(Long id);
    boolean isCourseBelongToCenter(Long id, Long centerId);
    boolean updateCenterIdForActiveCourse(Long currentCentId, Long newCenterId);
}
