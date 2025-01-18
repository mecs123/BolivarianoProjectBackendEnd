package com.bolivariano.course.service;

import com.bolivariano.course.dto.response.CourseResponseDTO;
import com.bolivariano.course.entities.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CourseService {
    List<CourseResponseDTO> findAllCourses();
    Optional<Course> findCourseById(Long id);
    Course saveCourse(Course course);
    Optional<Course> updateCourse(Long id, Course course);
    boolean deleteCourseById(Long id);
}