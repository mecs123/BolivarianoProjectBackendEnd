package com.bolivariano.teacher.controller;

import com.bolivariano.teacher.constans.ApiConstants;
import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.service.TeacherService;
import com.bolivariano.teacher.config.swagger.doc.SwaggerDocumentation;
import com.bolivariano.teacher.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/teacher-assign")
public class TeacherAssignmentController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/by-teacher-code")
    @SwaggerDocumentation.GetByCodeSubjectToTeacher.Documentation
    public ResponseEntity<WrapperResponse<?>>  getByCodeSubjectToTeacher(
            @RequestParam(name = "codTeacher") String codTeacher
    )  {
        TeacherResponseDTO teacher = teacherService.getTeacherDetailsByCode(codTeacher);
        WrapperResponse<TeacherResponseDTO> response = WrapperResponse.success(teacher, ApiConstants.RESPONSE_CODE_201,ApiConstants.SUCCESS_MESSAGE);
        return response.dinamicResponse(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @SwaggerDocumentation.putTeacher.Documentation
    public ResponseEntity<WrapperResponse<?>> put(
            @PathVariable ("id") Long id,
            @RequestBody TeacherRequestDTO input
    ) {
        TeacherResponseDTO updatedTeacher = teacherService.updateTeacher(input, id);
        WrapperResponse<TeacherResponseDTO> response = WrapperResponse.success(updatedTeacher, ApiConstants.RESPONSE_CODE_201,ApiConstants.TEACHER_UPDATE);
        return response.dinamicResponse(HttpStatus.OK);
    }
}
