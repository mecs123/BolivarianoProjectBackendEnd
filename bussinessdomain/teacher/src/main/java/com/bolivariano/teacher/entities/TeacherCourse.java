package com.bolivariano.teacher.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Entity
@Table(name = "TEACHER_COURSE")
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;
    @Column(name = "idCourse")
    private long idCourse;
    @Column(name = "nameCourse")
    private String nameCourse;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id")  // Clave foránea en TEACHER_COURSE
    @JsonBackReference  // Evita la recursión infinita al serializar Teacher
    private Teacher teacher;

    public TeacherCourse(long iD, long idCourse, String nameCourse, Teacher teacher) {
        this.iD = iD;
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.teacher = teacher;
    }

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
