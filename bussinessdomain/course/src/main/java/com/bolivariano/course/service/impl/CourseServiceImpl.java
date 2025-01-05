package com.bolivariano.course.service.impl;

import com.bolivariano.course.entities.Course;
import com.bolivariano.course.repository.CourseRepository;
import com.bolivariano.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * @return
     */
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    /**
     * @param course
     * @return
     */
    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * @param id
     * @param course
     * @return
     */
    @Override
    public Optional<Course> updateCourse(Long id, Course course) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setNameCourse(course.getNameCourse());
            return courseRepository.save(existingCourse);
        });
    }
    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
