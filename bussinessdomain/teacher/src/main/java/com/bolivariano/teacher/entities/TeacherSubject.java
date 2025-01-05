/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolivariano.teacher.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 *
 * @author AsusTUF
 */
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TEACHER_SUBJECT")
public class TeacherSubject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    @Column(name = "idSubject")
    private long idSubject;

    @Column(name = "nameSubject")
    private String nameSubject;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id")  // Clave foránea en TEACHER_SUBJECT
    @JsonBackReference  // Esto evita la recursión infinita durante la serialización
    private Teacher teacher;

    public TeacherSubject(long iD, long idSubject, String nameSubject, Teacher teacher) {
        this.iD = iD;
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
        this.teacher = teacher;
    }

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(long idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherSubject{" +
                "iD=" + iD +
                ", idSubject=" + idSubject +
                ", nameSubject='" + nameSubject + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
