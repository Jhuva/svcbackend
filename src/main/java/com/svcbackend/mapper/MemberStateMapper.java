package com.svcbackend.mapper;

import com.svcbackend.model.MemberStateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberStateMapper {
    List<MemberStateModel> findAllMemberState();
    MemberStateModel findByIdMemberState(@Param("idEstadoMiembro") Integer idEstado_miembro);
    void registerMemberState(MemberStateModel memberStateModel);
    void updateMemberState(MemberStateModel memberStateModel);
    void deleteMemberState(@Param("idEstadoMiembro") Integer idEstado_miembro);
}
