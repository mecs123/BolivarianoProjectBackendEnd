package com.user.securityApp.controller.dto;

import com.user.securityApp.persistence.entity.RoleEnum;

public class RoleDTO {

    private Long id;
    private RoleEnum roleName;  // Este es el valor de RoleEnum como cadena

    public RoleDTO(Long id, RoleEnum roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }
}
