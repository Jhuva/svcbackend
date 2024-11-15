package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.RolModel;
import com.svcbackend.response.GenericResponse;

public interface RolService {

    GenericResponse<Object> findAllRol() throws GenericException;
    GenericResponse<Object> findByIdRol(Integer idRol) throws GenericException;
    GenericResponse<Object> registerRol(RolModel rol);
    GenericResponse<Object> updateRol(RolModel rol);
    GenericResponse<Object> deleteRol(Integer idRol);

}
