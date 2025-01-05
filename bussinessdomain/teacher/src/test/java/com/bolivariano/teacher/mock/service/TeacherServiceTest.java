package com.bolivariano.teacher.mock.service;

import com.bolivariano.teacher.dto.request.TeacherRequestDTO;
import com.bolivariano.teacher.dto.response.TeacherResponseDTO;
import com.bolivariano.teacher.entities.Teacher;
import com.bolivariano.teacher.mapper.request.TeacherRequestMapper;
import com.bolivariano.teacher.mapper.response.TeacherResponseMapper;
import com.bolivariano.teacher.repository.TeacherRepository;
import com.bolivariano.teacher.service.factory.mapper.TeacherMapperFactory;
import com.bolivariano.teacher.service.factory.validation.impl.TeacherRestValidationImpl;
import com.bolivariano.teacher.service.impl.TeacherServiceImpl;
import com.bolivariano.teacher.mock.DataProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherResponseMapper teacherResponseMapper;

    @Mock
    private TeacherRequestMapper teacherRequestMapper;

    @Mock
    private TeacherMapperFactory teacherMapperFactory;

    @Mock
    private TeacherRestValidationImpl teacherValidation;


    @InjectMocks
    private TeacherServiceImpl teacherService;

    // Objetos globales
    private TeacherRequestDTO newTeacher;
    private Teacher saveTeacherEntityAlreadyFromRequest;
    private TeacherResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        newTeacher = DataProvider.newTeacher();
        saveTeacherEntityAlreadyFromRequest = DataProvider.saveTeacher();
        responseDTO = DataProvider.responseTeacherToDto();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void saveTeacherWith() {
        // Given
        TeacherRequestDTO newTeacher = DataProvider.newTeacher();  // Suponiendo que newTeacher es un TeacherRequestDTO
        TeacherResponseDTO responseDTO1 = DataProvider.responseTeacherToDtoMockito();

        // Configurar mocks
        doNothing().when(teacherValidation).validaCodeOfTeacher(newTeacher.getCodTeacher());
        when(teacherRepository.save(any(Teacher.class))).thenReturn(saveTeacherEntityAlreadyFromRequest);
        when(teacherResponseMapper.entityToResDto(any(Teacher.class))).thenReturn(responseDTO1);

        // Crear los capturadores de argumentos
        ArgumentCaptor<String> teacherCodeCaptor = ArgumentCaptor.forClass(String.class); // Para capturar el código del teacher
        ArgumentCaptor<Teacher> teacherCaptor = ArgumentCaptor.forClass(Teacher.class); // Para capturar el objeto Teacher en el repositorio

        // When
        TeacherResponseDTO response = teacherService.saveTeacherWith(newTeacher);

        // Then
        // Verificar que los métodos fueron llamados con los argumentos correctos
        verify(teacherValidation).validaCodeOfTeacher(teacherCodeCaptor.capture());
        verify(teacherRepository).save(teacherCaptor.capture());

        // Verificar los valores capturados
        assertNotNull(teacherCodeCaptor.getValue());
        assertEquals(newTeacher.getCodTeacher(), teacherCodeCaptor.getValue());  // Verificar que el código de teacher sea el correcto

        // Verificar que el objeto Teacher capturado es el correcto
        Teacher capturedTeacher = teacherCaptor.getValue();
        assertNotNull(capturedTeacher);
        assertEquals(newTeacher.getCodTeacher(), capturedTeacher.getCodTeacher());  // Asegurarse que el código coincide

        // Verificar la respuesta
        assertNotNull(response);
        assertEquals("T001", response.getCodTeacher());
        assertEquals("Manuel", response.getNameTeacher());
        assertTrue(response.isEstado());
    }

    @Test
    void findTeacherById() {
        // Given
        Teacher newTeacher = DataProvider.newTeacherEntityMock(); // Este es tu objeto Teacher simulado
        TeacherResponseDTO responseDTO = DataProvider.responseTeacherToDto();
        Long id = 1L;
        when(this.teacherRepository.findById(anyLong())).thenReturn(Optional.of(newTeacher));
        when(teacherResponseMapper.teacherToResponseDto(any(Teacher.class)))
                .thenReturn(responseDTO);
        // When
        TeacherResponseDTO teacher = this.teacherService.findTeacherById(id);
        // Then
        assertNotNull(teacher); // Verifica que no sea null
        assertEquals("T001", teacher.getCodTeacher());
        assertEquals("Manuel", teacher.getNameTeacher());
        assertTrue(teacher.isEstado());
    }






    @Test
    void findAllTeachers() {
    }



    @Test
    void deleteTeacher() {
    }

    @Test
    void getTeacherDetailsByCode() {
    }

    @Test
    void updateTeacher() {
    }

}