package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.MemberStateModel;
import com.svcbackend.response.GenericResponse;

public interface MemberStateService {
    GenericResponse<Object> findAllMemberState() throws GenericException;
    GenericResponse<Object> findByIdMemberState(Integer idMemberState) throws GenericException;
    GenericResponse<Object> registerMemberState(MemberStateModel memberState);
    GenericResponse<Object> updateMemberState(MemberStateModel memberState);
    GenericResponse<Object> deleteMemberState(Integer idMemberState);
}
