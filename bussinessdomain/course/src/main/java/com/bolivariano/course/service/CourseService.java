package com.bolivariano.course.service;

import com.bolivariano.course.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAllCourses();
    Optional<Course> findCourseById(Long id);
    Course saveCourse(Course course);
    Optional<Course> updateCourse(Long id, Course course);
    boolean deleteCourseById(Long id);
}