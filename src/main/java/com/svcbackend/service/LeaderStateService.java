package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.LeaderStateModel;
import com.svcbackend.response.GenericResponse;

public interface LeaderStateService {
    GenericResponse<Object> findAllLeaderState() throws GenericException;
    GenericResponse<Object> findByIdLeaderState(Integer idLeaderState) throws GenericException;
    GenericResponse<Object> registerLeaderState(LeaderStateModel leaderState);
    GenericResponse<Object> updateLeaderState(LeaderStateModel leaderState);
    GenericResponse<Object> deleteLeaderState(Integer idLeaderState);

}
