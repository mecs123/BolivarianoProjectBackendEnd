package com.user.securityApp.controller;

import com.user.securityApp.controller.dto.AuhthLoginRequest;
import com.user.securityApp.controller.dto.AuthCreateUserRequest;
import com.user.securityApp.controller.dto.AuthResponse;
import com.user.securityApp.controller.dto.UserListResponse;
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




}
