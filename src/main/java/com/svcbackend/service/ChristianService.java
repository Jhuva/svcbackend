package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ChristianModel;
import com.svcbackend.response.GenericResponse;

public interface ChristianService {
    GenericResponse<Object> findAllChristian() throws GenericException;
    GenericResponse<Object> findByIdChristian(Integer idCristiano) throws GenericException;
    GenericResponse<Object> findByIdChristianDetail(Integer idCristiano) throws GenericException;
    GenericResponse<Object> registerChristian(ChristianModel christian);
    GenericResponse<Object> updateChristian(ChristianModel christian);
    GenericResponse<Object> deleteChristian(Integer idCristiano);
}