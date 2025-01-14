package com.user.securityApp.service;



import com.auth0.jwt.interfaces.DecodedJWT;
import com.user.securityApp.consts.MessageConstants;
import com.user.securityApp.controller.dto.*;
import com.user.securityApp.persistence.entity.RoleEntity;
import com.user.securityApp.persistence.entity.UserEntity;
import com.user.securityApp.persistence.repository.PermisionRepository;
import com.user.securityApp.persistence.repository.RoleRepository;
import com.user.securityApp.persistence.repository.UserRepository;
import com.user.securityApp.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import java.util.*;
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

    private PermisionRepository permisionRepository;

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


        String refreshToken = jwtUtils.generateRefreshToken(username);
        AuthResponse authResponse = new AuthResponse(
                username,
                "User logged succesfully", 
                accesToken,
                refreshToken,
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
        String message = null;
        String username = createRoleRequest.username();
        String password = createRoleRequest.password();

        // Verificar si el nombre de usuario ya existe en la base de datos
        Optional<UserEntity> existingUser = userRepository.findUserEntityByUsername(username);
        if (existingUser.isPresent()) {
            message = MessageConstants.USER_ALREADY_EXIST;// Mensaje de error para el nombre de usuario duplicado
            throw new IllegalArgumentException(message); // Lanza un error con el mensaje
        }

        // Verificar si los roles son requeridos
        if (createRoleRequest.roleRequest() == null) {
            message = MessageConstants.ROLE_REQUEST_REQUIRED; // Usamos la constante
            throw new IllegalArgumentException(message);
        }

        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        // Buscar roles en la base de datos
        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest).stream()
                .collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            message = MessageConstants.ROLE_NOT_FOUND; // Usamos la constante
            throw new IllegalArgumentException(message);
        }

        // Crear el objeto UserEntity
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

        // Guardar el usuario en la base de datos
        UserEntity userSaved = userRepository.save(userEntity);

        // Asignar roles y permisos al usuario
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        // Crear la autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        // Crear el access token y refresh token
        String accessToken = jwtUtils.createToken(authentication, username);
        String refreshToken = jwtUtils.generateRefreshToken(username);

        // Crear el set de roles
        Set<String> roleNames = userSaved.getRoles().stream()
                .map(role -> role.getRoleEnum().name().replace("ROLE_", "")) // Eliminar prefijo "ROLE_"
                .collect(Collectors.toSet());

        // Establecer el mensaje de éxito
        message = MessageConstants.USER_CREATED; // Usamos la constante para el mensaje de éxito

        // Crear y devolver la respuesta
        AuthResponse authResponse = new AuthResponse(
                username,
                message,
                accessToken,
                refreshToken,
                roleNames,  // Pasar la lista de roles como String
                true
        );
        return authResponse;
    }

    public List<UserListResponse> listAllUsers() {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();  // Obtén la lista de usuarios desde el repositorio

        return users.stream()
                .map(user -> new UserListResponse(
                        user.getId(),
                        user.getUsername(),
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

    public UserRolePermissionDTO updateUser(AuthUpdateUserRequest createRoleRequest, Long id) {

        // Buscar el usuario existente
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario con ID " + id + " no encontrado"));

        // Validar los roles proporcionados en la solicitud
        if (createRoleRequest.roleRequest() == null) {
            throw new IllegalArgumentException("Role request is required");
        }

        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        // Obtener roles desde la base de datos que coinciden con la lista de roles proporcionada
        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest)
                .stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified do not exist.");
        }

        // Actualizar los valores del usuario
        user.setUsername(createRoleRequest.username());
        user.setFullName(createRoleRequest.fullName());
        user.setEmail(createRoleRequest.email());
        user.setDateOfBirth(createRoleRequest.dateOfBirth());
        user.setPhone(createRoleRequest.phone());
        user.setEnabled(createRoleRequest.isEnabled());
        user.setAccountNoLocked(createRoleRequest.accountNoLocked());
        user.setAccountNoExpired(createRoleRequest.accountNoExpired());
        user.setCredentialNoExpired(createRoleRequest.credentialNoExpired());
        user.setRoles(roleEntityList); // Asignamos los roles a la entidad

        // Guardar el usuario actualizado
        UserEntity userSaved = userRepository.save(user);

        // Generar lista de autoridades (roles y permisos)
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userSaved.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream()) // Extraemos permisos asociados
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        // Crear la respuesta con los roles y permisos actualizados
        Set<String> roleNames = userSaved.getRoles().stream()
                .map(role -> role.getRoleEnum().name().replace("ROLE_", "")) // Eliminar prefijo "ROLE_"
                .collect(Collectors.toSet());



        // Devolver DTO con la información actualizada
        return new UserRolePermissionDTO(
                id,
                userSaved.getUsername(),
                user.getFullName(),
                user.getEmail(),
                userSaved.getDateOfBirth(),
                user.getPhone(),
                userSaved.isEnabled(),
                userSaved.isAccountNoExpired(),
                userSaved.isAccountNoLocked(),
                roleNames
        );
    }


    public boolean deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Usuario con el "+id+" No existe"));

        userEntity.getRoles().forEach(role->{
            role.getPermissionList().clear();
            roleRepository.save(role);
        });
        userEntity.getRoles().clear();
        userRepository.save(userEntity);

        userRepository.delete(userEntity);

        return true;
    }

    public UserListResponse listUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario con el id " + id + " no existe"));

        return new UserListResponse(
                        user.getId(),
                        user.getUsername(),
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
        );

    }


    public List<RoleDTO> listAllRoles() {
        // Asegúrate de que la consulta en el repositorio sea correcta.
        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();

        // Mapeamos los RoleEntity a RoleDTO y los recolectamos en una lista.
        return roles.stream()
                .map(roleEntity -> new RoleDTO(
                        roleEntity.getId(),
                        roleEntity.getRoleEnum()  // Suponiendo que RoleDTO tiene estos dos campos.
                ))
                .collect(Collectors.toList());
    }

    public Object refreshToken1(String refreshToken) {

        try {
            // Validar el refresh token
            DecodedJWT decodedJWT = jwtUtils.validateToken(refreshToken);
            String username = decodedJWT.getSubject();

            if (username != null){
                // Generar un nuevo Access Token
                String newAccessToken = jwtUtils.generateRefreshToken(username);  // Puedes ajustar 'null' según tu lógica de roles

                return ResponseEntity.ok().body(newAccessToken);
            }else {
                return ResponseEntity.status(400).body("usuario no valido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Refresh token inválido o expirado.");
        }
    }




}
