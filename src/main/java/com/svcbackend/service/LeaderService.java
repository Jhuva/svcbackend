package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.LeaderModel;
import com.svcbackend.response.GenericResponse;

public interface LeaderService {
    GenericResponse<Object> findAllLeader() throws GenericException;
    GenericResponse<Object> findByIdLeader(Integer idLider) throws GenericException;
    GenericResponse<Object> registerLeader(LeaderModel leader);
    GenericResponse<Object> updateLeader(LeaderModel leader);
    GenericResponse<Object> deleteLeader(Integer idLider);
}
