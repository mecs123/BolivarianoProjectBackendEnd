/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.bolivariano.usuario.com.bolivariano.usuario.repository;

import com.bolivariano.usuario.com.bolivariano.usuario.entities.Usuario;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Manuel Catro
 */
public interface UserRepository extends JpaRepository<Usuario, Id> {
    
}
