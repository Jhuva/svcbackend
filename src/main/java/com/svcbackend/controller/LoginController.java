package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.UserModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public GenericResponse<Object> login(@RequestParam String username, @RequestParam String password) throws GenericException {
        return loginService.login(username, password);
    }

    @PostMapping("/register")
    public GenericResponse<Object> register(@RequestBody UserModel user) throws GenericException {
        return loginService.register(user);
    }

}
