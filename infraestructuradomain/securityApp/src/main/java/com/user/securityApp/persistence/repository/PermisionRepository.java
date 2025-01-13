package com.user.securityApp.persistence.repository;

import com.user.securityApp.persistence.entity.PermissionEntity;
import com.user.securityApp.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisionRepository extends CrudRepository<PermissionEntity, Long> {

}