package com.bolivariano.teacher.service;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import org.springframework.data.domain.Page;

public interface TeacherService {

    TeacherResponseDTO findTeacherById(Long id);

    TeacherResponseDTO saveTeacherWith(TeacherRequestDTO teacherRequestDTO);
    TeacherResponseDTO deleteTeacher(Long id);
    
    TeacherResponseDTO getTeacherDetailsByCode(String codTeacher);

    TeacherResponseDTO updateTeacher(TeacherRequestDTO input, Long id);


    Page<TeacherResponseDTO> findTeachersWithPagination(int page, int size);
}
