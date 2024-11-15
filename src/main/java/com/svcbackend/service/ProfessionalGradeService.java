package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ProfessionalGradeModel;
import com.svcbackend.response.GenericResponse;

public interface ProfessionalGradeService {
    GenericResponse<Object> findAllProfessionalGrade() throws GenericException;
    GenericResponse<Object> findByIdProfessionalGrade(Integer idProfessionalGrade) throws GenericException;
    GenericResponse<Object> registerProfessionalGrade(ProfessionalGradeModel ProfessionalGrade);
    GenericResponse<Object> updateProfessionalGrade(ProfessionalGradeModel ProfessionalGrade);
    GenericResponse<Object> deleteProfessionalGrade(Integer idProfessionalGrade);
}
