package com.user.securityApp.controller;

import com.user.securityApp.controller.dto.*;
import com.user.securityApp.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailsService;


    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest) throws RoleNotFoundException {
        return new ResponseEntity<>(this.userDetailsService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> loging(@RequestBody @Valid AuhthLoginRequest userRequest){
        return  new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListResponse>> allUser(){
        return new ResponseEntity<>(this.userDetailsService.listAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserRolePermissionDTO> update(
            @PathVariable ("id") Long id,
            @RequestBody @Valid AuthUpdateUserRequest userRequest
    ) throws RoleNotFoundException {
        return new ResponseEntity<>(this.userDetailsService.updateUser(userRequest,id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable ("id") Long id
    ) throws RoleNotFoundException {

        if (userDetailsService.deleteUser(id)){
              return ResponseEntity.ok().build();
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserListResponse> allUserById(
            @PathVariable("id") Long id
    ){
        return new ResponseEntity<>(this.userDetailsService.listUserById(id), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> allRoles(
    ){
        return new ResponseEntity<>(this.userDetailsService.listAllRoles(), HttpStatus.OK);
    }




}
