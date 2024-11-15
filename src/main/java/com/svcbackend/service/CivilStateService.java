package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CivilStateModel;
import com.svcbackend.response.GenericResponse;

public interface CivilStateService {

    GenericResponse<Object> findAllCivilState() throws GenericException;
    GenericResponse<Object> findByIdCivilState(Integer idCivilState) throws GenericException;
    GenericResponse<Object> registerCivilState(CivilStateModel civilState);
    GenericResponse<Object> updateCivilState(CivilStateModel civilState);
    GenericResponse<Object> deleteCivilState(Integer idCivilState);

}
