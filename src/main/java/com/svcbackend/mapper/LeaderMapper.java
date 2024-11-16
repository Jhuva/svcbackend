package com.svcbackend.mapper;

import com.svcbackend.model.LeaderAllModel;
import com.svcbackend.model.LeaderDetailModel;
import com.svcbackend.model.LeaderModel;
import com.svcbackend.model.LeaderNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaderMapper {
    List<LeaderModel> findAllLeader();
    List<LeaderAllModel> findAllTableLeader();
    LeaderNumModel findLeaderNumbers();
    LeaderModel findByIdLeader(@Param("idLider") Integer idLider);
    LeaderDetailModel findByIdLeaderDetail(@Param("idLider") Integer idLider);
    void registerLeader(LeaderModel leaderModel);
    void updateLeader(LeaderModel leaderModel);
    void deleteLeader(@Param("idLider") Integer idLider);
}
