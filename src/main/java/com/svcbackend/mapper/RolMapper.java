package com.svcbackend.mapper;

import com.svcbackend.model.RolModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolMapper {

    List<RolModel> findAllRol();
    RolModel findByIdRol(@Param("idRol") Integer idRol);
    void registerRol(RolModel rolModel);
    void updateRol(RolModel rolModel);
    void deleteRol(@Param("idRol") Integer idRol);

}
