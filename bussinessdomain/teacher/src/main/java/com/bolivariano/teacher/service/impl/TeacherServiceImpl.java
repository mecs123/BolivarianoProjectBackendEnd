package com.bolivariano.teacher.service.impl;

import com.bolivariano.teacher.constans.ApiConstants;
import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherCourseResponseDto;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.dto.response.TeacherSubjectResponseDto;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.exception.NoDataFoundExepcion;
import com.bolivariano.teacher.mapper.response.TeacherResponseMapper;
import com.bolivariano.teacher.repository.TeacherRepository;
import com.bolivariano.teacher.service.TeacherService;
import com.bolivariano.teacher.service.factory.validation.TeacherFactory;
import com.bolivariano.teacher.service.factory.validation.impl.TeacherAssignmentValidationImpl;
import com.bolivariano.teacher.service.factory.validation.impl.TeacherRestValidationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository tRepository;
    private final TeacherResponseMapper resMapper;
    private final TeacherRestValidationImpl implValida;
    private final TeacherFactory teacherFactory;
    private final TeacherAssignmentValidationImpl restCommunication;

    @Autowired
    public TeacherServiceImpl(
            TeacherRepository tRepository,
            TeacherResponseMapper resMapper,
            TeacherRestValidationImpl implValida, TeacherFactory teacherFactory,
            TeacherAssignmentValidationImpl restCommunication
    ) {
        this.tRepository = tRepository;
        this.resMapper = resMapper;
        this.implValida = implValida;
        this.teacherFactory = teacherFactory;
        this.restCommunication = restCommunication;
    }

    @Override
    public TeacherResponseDTO saveTeacherWith(TeacherRequestDTO teacherRequestDTO) {
        implValida.validaCodeOfTeacher(teacherRequestDTO.getCodTeacher());
        Teacher teacherPersist = teacherFactory.createTeacher(teacherRequestDTO);
        Teacher savedTeacher = tRepository.save(teacherPersist);
        return resMapper.entityToResDto(savedTeacher);
    }

    @Override
    public TeacherResponseDTO findTeacherById(Long id) {
        Teacher teacher = tRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundExepcion(
                        ApiConstants.ERROR_CODE_TEACHER_NOT_FOUND,
                        ApiConstants.INVALID_TEACHER_ID,
                        HttpStatus.NOT_FOUND));
        return resMapper.teacherToResponseDto(teacher);
    }

    @Override
    public TeacherResponseDTO getTeacherDetailsByCode(String codTeacher) {

        Teacher teacher = implValida.validateCodeTeacher(tRepository.findByCodeTeacher(codTeacher),codTeacher);
        TeacherResponseDTO resDTO = resMapper.teacherToResponseDto(teacher);

        Set<TeacherSubjectResponseDto> subjectResDto = restCommunication.fetchSubjectList(teacher.getListSubjectByTeacher());
        Set<TeacherCourseResponseDto> courseResDto = restCommunication.fetchCourseList(teacher.getTeacherCourseList());

        resDTO.setTeacherSubjectResponseDto(resMapper.mapSubjects(subjectResDto));
        resDTO.setTeacherCourseResponseDto(resMapper.mapCourses(courseResDto));
        return resDTO;
    }

    @Override
    public TeacherResponseDTO updateTeacher(TeacherRequestDTO requestDTO, Long id) {
        tRepository.findById(id).orElseThrow(() -> new NoDataFoundExepcion(ApiConstants.TEACHER_NOT_FOUND));
        Teacher teacher = teacherFactory.updateTeacher(requestDTO,id);
        Teacher teacherResponse = tRepository.save(teacher);
        return resMapper.teacherToResponseDto(teacherResponse);
    }

    @Override
    public Page<TeacherResponseDTO> findTeachersWithPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size).withSort(sort);
        Page<Teacher> teacherPage = tRepository.findAllTeacher(pageable);

        List<TeacherResponseDTO> responseDTOs = teacherPage.getContent()
                .stream()
                .map(resMapper::entityToResDto)
                .toList();
        return new PageImpl<>(responseDTOs, pageable, teacherPage.getTotalElements());
    }

    @Override
    public TeacherResponseDTO deleteTeacher(Long id) {
        try {
            tRepository.deleteById(id);
            return new TeacherResponseDTO(id,
                    null,
                    null,
                    false,
                    null,
                    null
            );
        } catch (Exception e) {
            System.out.println("Error deleting teacher with id " + id + ": " + e.getMessage());
           return new TeacherResponseDTO(id,
                   "Failed to delete teacher",
                   null,
                   false,
                   null,
                   null
           );
        }
    }

}