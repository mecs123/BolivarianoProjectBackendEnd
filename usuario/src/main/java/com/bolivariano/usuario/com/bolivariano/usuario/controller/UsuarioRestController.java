/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.bolivariano.usuario.com.bolivariano.usuario.controller;

import com.bolivariano.usuario.com.bolivariano.usuario.entities.Usuario;
import com.bolivariano.usuario.com.bolivariano.usuario.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author AsusTUF
 */
@RestController
@RequestMapping("/user")
public class UsuarioRestController {
    
    @Autowired
    UserRepository userRepositoryInject;
    
    @GetMapping()
    public List<Usuario> list() {
        return userRepositoryInject.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Usuario input) {

        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Usuario input) {
        Usuario user = userRepositoryInject.save(input);
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
