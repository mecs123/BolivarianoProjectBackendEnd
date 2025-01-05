package com.bolivariano.course.dto.request;

import com.bolivariano.course.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseRequestDTO {
    private String nameCourse;

    // Método de conversión a entidad Course
    public Course toEntity() {
        Course course = new Course();
        course.setNameCourse(this.nameCourse);
        return course;
    }
}
