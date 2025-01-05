/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.bolivariano.subject.repository;

import com.bolivariano.subject.entities.SubjectCollege;
import jakarta.persistence.Id;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AsusTUF
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectCollege, Id> {

    public void deleteById(Long id);

    public Optional<SubjectCollege> findById(Long id);

    public SubjectCollege existsById(Long id);
    
}
