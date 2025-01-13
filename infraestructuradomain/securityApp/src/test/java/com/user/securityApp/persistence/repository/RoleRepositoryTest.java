package com.user.securityApp.persistence.repository;

import com.user.securityApp.persistence.entity.PermissionEntity;
import com.user.securityApp.persistence.entity.RoleEntity;
import com.user.securityApp.persistence.entity.RoleEnum;
import com.user.securityApp.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    TestEntityManager testEntityManager;


    PermissionEntity permission;
    RoleEntity role;
    UserEntity userEntity;

    @BeforeEach
    void setUp() {

        // Creación del permiso
        permission = new PermissionEntity();
        permission.setName("READ");

        // Asignar rol y asociar el permiso
        role = new RoleEntity();
        role.setRoleEnum(RoleEnum.ADMIN);
        role.setPermissionList(Set.of(permission));


        // Crear usuario y asociar rol
        userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setEmail("test@example.com");
        userEntity.setFullName("Test User");
        userEntity.setPassword("password");  // Simulación de contraseña
        userEntity.setEnabled(true);
        userEntity.setRoles(Set.of(role));
    }

    @Test
    void givenTestFindRoleEntitiesByRoleEnumIn() {
        // given
        List<String> roles = List.of("ADMIN");
        testEntityManager.persist(role);
        // when

        Iterable<RoleEntity> result = roleRepository.findRoleEntitiesByRoleEnumIn(roles);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).extracting(RoleEntity::getRoleEnum).contains(RoleEnum.ADMIN);

    }
}