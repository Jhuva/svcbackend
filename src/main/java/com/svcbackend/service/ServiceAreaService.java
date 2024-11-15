package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ServiceAreaModel;
import com.svcbackend.response.GenericResponse;

public interface ServiceAreaService {

    GenericResponse<Object> findAllServiceArea() throws GenericException;
    GenericResponse<Object> findByIdServiceArea(Integer idServiceArea) throws GenericException;
    GenericResponse<Object> registerServiceArea(ServiceAreaModel serviceArea);
    GenericResponse<Object> updateServiceArea(ServiceAreaModel serviceArea);
    GenericResponse<Object> deleteServiceArea(Integer idServiceArea);

}
