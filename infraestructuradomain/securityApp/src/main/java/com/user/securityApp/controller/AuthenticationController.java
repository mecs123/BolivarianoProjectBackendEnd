package com.user.securityApp.controller;

import com.user.securityApp.controller.dto.*;
import com.user.securityApp.exception.SecurityErrorHandler;
import com.user.securityApp.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailsService;


    @PostMapping(value = "demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }

    @PostMapping("/sign-up")
    @HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest)  {
        return new ResponseEntity<>(this.userDetailsService.createUser(userRequest), HttpStatus.CREATED);
    }


    @PostMapping("/log-in")
    @HandleAuthorizationDenied(handlerClass = SecurityErrorHandler.class)
    public ResponseEntity<AuthResponse> loging(@RequestBody @Valid AuhthLoginRequest userRequest){

        return  new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        return new ResponseEntity<>(this.userDetailsService.refreshToken1(refreshToken),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListResponse>> allUser(){
        return new ResponseEntity<>(this.userDetailsService.listAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserRolePermissionDTO> update(
            @PathVariable ("id") Long id,
            @RequestBody @Valid AuthUpdateUserRequest userRequest)  {
        return new ResponseEntity<>(this.userDetailsService.updateUser(userRequest,id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id)  {
        if (userDetailsService.deleteUser(id)){
              return ResponseEntity.ok().build();
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserListResponse> allUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.userDetailsService.listUserById(id), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> allRoles(){
        return new ResponseEntity<>(this.userDetailsService.listAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<AuthEmailResponse> getEmail(@RequestParam ("email") String email) {
        AuthEmailResponse exists = this.userDetailsService.obtainEmail(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }





}
