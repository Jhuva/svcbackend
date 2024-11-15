package com.svcbackend.mapper;

import com.svcbackend.model.LeaderStateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaderStateMapper {
    List<LeaderStateModel> findAllLeaderState();
    LeaderStateModel findByIdLeaderState(@Param("idEstadoLider") Integer idEstado_lider);
    void registerLeaderState(LeaderStateModel leaderStateModel);
    void updateLeaderState(LeaderStateModel leaderStateModel);
    void deleteLeaderState(@Param("idEstadoLider") Integer idEstado_lider);
}
