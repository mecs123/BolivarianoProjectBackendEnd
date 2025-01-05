package com.bolivariano.teacher.controller;

import com.bolivariano.teacher.config.swagger.doc.SwaggerDocumentation;
import com.bolivariano.teacher.constans.ApiConstants;
import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.service.TeacherService;
import com.bolivariano.teacher.config.swagger.doc.TeacherSwaggerConfig;
import com.bolivariano.teacher.utils.WrapperResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ComponentScan({"com.bolivariano.teacher"})
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherRestController implements TeacherSwaggerConfig {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private Environment env;

    @GetMapping("/check")
    public String check() {
        return "Hello, your environment value is : " +
                env.getProperty("custom.activeprofileName");
    }

    @GetMapping("/all")
    @SwaggerDocumentation.GetAllTeacher.Documentation
    public WrapperResponse<Page<TeacherResponseDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<TeacherResponseDTO> teachers = teacherService.findTeachersWithPagination(page,size);
        WrapperResponse<TeacherResponseDTO> response = new WrapperResponse<>(
                ApiConstants.TEACHER_SUCCES,
                ApiConstants.RESPONSE_CODE_200,
                null
        );
        return response.getAllResponse(HttpStatus.OK, teachers);
    }

    @PostMapping
    @SwaggerDocumentation.postTeacher.Documentation
    public ResponseEntity<WrapperResponse<?>> post(
            @RequestBody @Valid TeacherRequestDTO input
    ) {
        TeacherResponseDTO savedTeacher = teacherService.saveTeacherWith(input);
        WrapperResponse<TeacherResponseDTO> response = WrapperResponse.success(
                savedTeacher,
                ApiConstants.RESPONSE_CODE_201,
                ApiConstants.TEACHER_CREATED
        );
        return response.dinamicResponse(HttpStatus.CREATED);
    }

    // Obtener un profesor por ID
    @GetMapping("/{id}")
    @SwaggerDocumentation.getByTeacher.Documentation
    public ResponseEntity<WrapperResponse<?>> get(
            @PathVariable("id") Long id
    ) {
        TeacherResponseDTO savedTeacher = teacherService.findTeacherById(id);
        WrapperResponse<TeacherResponseDTO> response = WrapperResponse.success(
                savedTeacher,
                ApiConstants.RESPONSE_CODE_200,
                ApiConstants.TEACHER_FOUND
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @SwaggerDocumentation.deleteByTeacher.Documentation
    public ResponseEntity<WrapperResponse<?>> delete(
            @PathVariable("id") Long id
    ) {
        TeacherResponseDTO teacherResponseDTO = teacherService.deleteTeacher(id);
        WrapperResponse<TeacherResponseDTO> response = WrapperResponse.success(
                teacherResponseDTO,
                ApiConstants.RESPONSE_CODE_200,
                ApiConstants.TEACHER_DELETED
        );
        return response.dinamicResponse(HttpStatus.OK);
    }

}