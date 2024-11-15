package com.svcbackend.mapper;

import com.svcbackend.model.ProfessionalGradeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProfessionalGradeMapper {
    List<ProfessionalGradeModel> findAllProfessionalGrade();
    ProfessionalGradeModel findByIdProfessionalGrade(@Param("idGradoProfesional") Integer idGrado_profesional);
    void registerProfessionalGrade(ProfessionalGradeModel professionalGradeModel);
    void updateProfessionalGrade(ProfessionalGradeModel professionalGradeModel);
    void deleteProfessionalGrade(@Param("idGradoProfesional") Integer idGrado_profesional);
}
