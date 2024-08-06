package com.example.freshermanagement.repository;

import com.example.freshermanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.endDate > CURRENT_DATE")
    Optional<Course> findByIdAndEndDateAfterNow(@Param("id") Long id);

    Optional<Course> findByIdAndCenterId(Long id, Long centerId);

    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.center.id = :newCenterId WHERE c.center.id = :currentCenterId AND c.endDate > CURRENT_DATE")
    void updateCenterIdForActiveCourses(@Param("currentCenterId")Long currentCenterId, @Param("newCenterId")Long newCenterId);
}
