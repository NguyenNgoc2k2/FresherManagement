package com.example.freshermanagement.service.impl;

import com.example.freshermanagement.entity.Course;
import com.example.freshermanagement.exception.ResourceNotFoundException;
import com.example.freshermanagement.repository.CourseRepository;
import com.example.freshermanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public boolean save(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public Course getActiveCourseById(Long id) {
        return courseRepository.findByIdAndEndDateAfterNow(id)
                .orElseThrow(() -> {
                    log.error("Course not found with id: " + id);
                    return new ResourceNotFoundException("Course not found with id: " + id);
                });
    }

    @Override
    public boolean isCourseBelongToCenter(Long id, Long centerId) {
        Optional<Course> course = courseRepository.findByIdAndCenterId(id, centerId);
        return course.isPresent();
    }

    @Override
    public boolean updateCenterIdForActiveCourse(Long currentCentId, Long newCenterId) {
        courseRepository.updateCenterIdForActiveCourses(currentCentId, newCenterId);
        return true;
    }

}