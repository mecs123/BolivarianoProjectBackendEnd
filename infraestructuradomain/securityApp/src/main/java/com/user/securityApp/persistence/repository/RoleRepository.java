package com.user.securityApp.persistence.repository;

import com.user.securityApp.controller.dto.AuthResponse;
import com.user.securityApp.controller.dto.UserRolePermissionDTO;
import com.user.securityApp.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);

    @Query(value = "SELECT id, role_name roles",nativeQuery = true)
    List<RoleEntity> findAllRoleEntities();




}