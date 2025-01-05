package com.user.securityApp.service;



import com.user.securityApp.controller.dto.*;
import com.user.securityApp.persistence.entity.RoleEntity;
import com.user.securityApp.persistence.entity.UserEntity;
import com.user.securityApp.persistence.repository.RoleRepository;
import com.user.securityApp.persistence.repository.UserRepository;
import com.user.securityApp.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuhthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Crear el token
        String accesToken = jwtUtils.createToken(authentication, username);

        // Extraer roles del usuario desde las autoridades
        Set<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)  // Obtener la autoridad (que tiene el prefijo "ROLE_")
                .filter(authority -> authority.startsWith("ROLE_"))  // Filtrar solo las autoridades que tienen el prefijo "ROLE_"
                .map(authority -> authority.replace("ROLE_", ""))  // Eliminar el prefijo "ROLE_"
                .collect(Collectors.toSet());  // Convertir a una lista de strings

        AuthResponse authResponse = new AuthResponse(
                username,
                "User logged succesfully", 
                accesToken,
                roles,
                true);
        
        return authResponse;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw  new BadCredentialsException("Invalid username or Password");
        }

        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();

        if (createRoleRequest.roleRequest() == null) {
            throw new IllegalArgumentException("Role request is required");
        }

        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest).stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .fullName(createRoleRequest.fullName())
                .email(createRoleRequest.email())
                .dateOfBirth(createRoleRequest.dateOfBirth())
                .phone(createRoleRequest.phone())
                .isEnabled(createRoleRequest.isEnabled())
                .accountNoLocked(createRoleRequest.accountNoLocked())
                .accountNoExpired(createRoleRequest.accountNoExpired())
                .credentialNoExpired(createRoleRequest.credentialNoExpired())
                .roles(roleEntityList)
                .build();

        UserEntity userSaved = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication, username);

        // Modificar aquí: devolver los roles como lista de strings en lugar de AuthRoleResponse
        Set<String> roleNames = userSaved.getRoles().stream()
                .map(role -> role.getRoleEnum().name().replace("ROLE_", ""))  // Eliminar prefijo "ROLE_"
                .collect(Collectors.toSet());

        // Crear la respuesta con la lista de roles como String
        AuthResponse authResponse = new AuthResponse(
                username,
                "User created successfully",
                accessToken,
                roleNames,  // Pasar la lista de roles en lugar de AuthRoleResponse
                true
        );
        return authResponse;
    }

    public List<UserListResponse> listAllUsers() {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();  // Obtén la lista de usuarios desde el repositorio

        return users.stream()
                .map(user -> new UserListResponse(
                        user.getUsername(),
                        user.getPassword(),
                        user.getFullName(),
                        user.getDateOfBirth(),
                        user.getEmail(),
                        user.getPhone(),
                        user.isEnabled(),
                        user.isAccountNoExpired(),
                        user.isAccountNoLocked(),
                        user.isCredentialNoExpired(), // Agregado aquí
                        user.getRoles().stream()
                                .map(role -> role.getRoleEnum().name().replace("ROLE_", "")) // Obtener solo el nombre del rol, sin el prefijo "ROLE_"
                                .collect(Collectors.toList()) // Cambiar a List<String> en lugar de Set<AuthRoleResponse>
                ))
                .collect(Collectors.toList());

    }


}
