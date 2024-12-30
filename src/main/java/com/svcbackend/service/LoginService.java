package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.UserModel;
import com.svcbackend.response.GenericResponse;

public interface LoginService {
    GenericResponse<Object> login(String username, String password) throws GenericException;
    GenericResponse<Object> register(UserModel user);
}
