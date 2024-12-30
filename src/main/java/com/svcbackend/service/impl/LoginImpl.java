package com.svcbackend.service.impl;

import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.UserMapper;
import com.svcbackend.model.UserModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.security.JwtService;
import com.svcbackend.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginImpl implements LoginService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public GenericResponse<Object> login(String username, String password) throws GenericException {
        UserModel user = userMapper.loginUser(username);
        try {
            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                return new GenericResponse<>(false, null, "Usuario o contraseña incorrecta");
            }
            String token = jwtService.tokenGenerate(user.getUsername(), user.getName(), user.getRole(), user.getSexo());
            return new GenericResponse<>(true, token, "Inició sesión correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> register(UserModel user) {
        log.info("Registrando a un usuario {}... ", user.getUsername());
        try {
            UserModel exist = userMapper.loginUser(user.getUsername());
            if(exist != null) {
                return new GenericResponse<>(false, null, "El usuario ya está registrado");
            }
            if (!esCorreoValido(user.getUsername())) {
                return new GenericResponse<>(false, null, "No es un correo válido");
            }
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userMapper.registerUser(user);
            return new GenericResponse<>(true, "Se ha registrado el usuario correctamente");

        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar");
        }
    }

    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return correo.matches(regex);
    }

}
