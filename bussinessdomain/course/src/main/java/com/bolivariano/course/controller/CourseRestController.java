package com.bolivariano.course.controller;

import com.bolivariano.course.dto.request.CourseRequestDTO;
import com.bolivariano.course.dto.response.CourseResponseDTO;
import com.bolivariano.course.entities.Course;
import com.bolivariano.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> listAllCourses(

    ) {
        List<Course> courses = courseService.findAllCourses();

        List<CourseResponseDTO> response = courses.stream()
                .map(CourseResponseDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(
            @PathVariable("id") Long id
    ) {
        Optional<Course> course = courseService.findCourseById(id);
        return course
                .map(c -> ResponseEntity.ok(CourseResponseDTO.fromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody CourseRequestDTO requestDTO
    ) {
        Course course = courseService.saveCourse(requestDTO.toEntity());
        return ResponseEntity.ok(CourseResponseDTO.fromEntity(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO requestDTO
    ) {
        Optional<Course> updatedCourse = courseService.updateCourse(id, requestDTO.toEntity());
        return updatedCourse
                .map(c -> ResponseEntity.ok(CourseResponseDTO.fromEntity(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id
    ) {
        if (courseService.deleteCourseById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
