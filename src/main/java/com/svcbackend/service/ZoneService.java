package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ZoneModel;
import com.svcbackend.response.GenericResponse;

public interface ZoneService {
    GenericResponse<Object> findAllZone() throws GenericException;
    GenericResponse<Object> findByIdZone(Integer idZona) throws GenericException;
    GenericResponse<Object> registerZone(ZoneModel zone);
    GenericResponse<Object> updateZone(ZoneModel zone);
    GenericResponse<Object> deleteZone(Integer idZona);
}
