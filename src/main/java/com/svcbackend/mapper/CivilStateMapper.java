package com.svcbackend.mapper;

import com.svcbackend.model.CivilStateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CivilStateMapper {

    List<CivilStateModel> findAllCivilState();
    CivilStateModel findByIdCivilState(@Param("idEstadoCivil") Integer idEstado_civil);
    void registerCivilState(CivilStateModel civilStateModel);
    void updateCivilState(CivilStateModel civilStateModel);
    void deleteCivilState(@Param("idEstadoCivil") Integer idEstado_civil);

}
