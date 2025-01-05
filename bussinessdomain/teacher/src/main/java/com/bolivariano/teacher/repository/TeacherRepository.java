/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.bolivariano.teacher.repository;

import com.bolivariano.teacher.entities.Teacher;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AsusTUF
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public void deleteById(Long id);

    public Optional<Teacher> findById(Long id);

    @Query("SELECT t FROM Teacher t WHERE t.codTeacher = :codTeacher")
    Teacher findByCodeTeacher(@Param("codTeacher") String codTeacher);

    @Query(value = "SELECT te FROM Teacher te " +
            "LEFT JOIN FETCH te.listSubjectByTeacher " +
            "LEFT JOIN FETCH te.teacherCourseList",
            countQuery = "SELECT COUNT(te) FROM Teacher te")
    Page<Teacher> findAllTeacher(Pageable pageable);

}
