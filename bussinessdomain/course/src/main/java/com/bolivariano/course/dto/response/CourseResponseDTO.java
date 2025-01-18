package com.bolivariano.course.dto.response;

import com.bolivariano.course.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseResponseDTO {
    private Long id;
    private String nameCourse;




    public static CourseResponseDTO fromEntity(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setNameCourse(course.getNameCourse());
        return dto;
    }


}
