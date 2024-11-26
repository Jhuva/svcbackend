package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.SupervisionModel;
import com.svcbackend.response.GenericResponse;

public interface SupervisionService {
    GenericResponse<Object> findAllSupervisionService() throws GenericException;
    GenericResponse<Object> findAllTableSupervision() throws GenericException;
    GenericResponse<Object> findSupervisionNumbers() throws GenericException;
    GenericResponse<Object> findByIdSupervisionService(Integer idSupervision) throws GenericException;
    GenericResponse<Object> findByIdSupervisionDetail(Integer idSupervision) throws GenericException;
    GenericResponse<Object> registerSupervisionService(SupervisionModel supervision);
    GenericResponse<Object> updateSupervisionService(SupervisionModel supervision);
    GenericResponse<Object> deleteSupervisionService(Integer idSupervision);
}
