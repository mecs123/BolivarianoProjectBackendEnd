package com.bolivariano.student.utils;

import com.bolivariano.student.entities.Student;
import com.bolivariano.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    /**
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        log.info("StudentApplication... loading data");

        studentRepository.saveAll(getStudentList());


        log.info("StudentApplication... Data loaded");

    }

    private Iterable<Student> getStudentList() {

        var student = new Student().builder()
                .codeStudent("001")
                .nameStudent("Juan")
                .build();


        var student2 = new Student().builder()
                .codeStudent("002")
                .nameStudent("Pedro")
                .build();

        var student3 = new Student().builder()
                .codeStudent("003")
                .nameStudent("Pablo")
                .build();
        var student4 = new Student().builder()
                .codeStudent("004")
                .nameStudent("Maria")
                .build();


        return List.of(student, student2, student3, student4);
    }
}
