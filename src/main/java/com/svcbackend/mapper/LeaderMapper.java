package com.svcbackend.mapper;

import com.svcbackend.model.LeaderModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaderMapper {
    List<LeaderModel> findAllLeader();
    LeaderModel findByIdLeader(@Param("idLider") Integer idLider);
    void registerLeader(LeaderModel leaderModel);
    void updateLeader(LeaderModel leaderModel);
    void deleteLeader(@Param("idLider") Integer idLider);
}
