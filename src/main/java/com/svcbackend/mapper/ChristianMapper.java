package com.svcbackend.mapper;

import com.svcbackend.model.ChristianDetailModel;
import com.svcbackend.model.ChristianModel;
import com.svcbackend.model.ChristianNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChristianMapper {

    List<ChristianModel> findAllChristian();
    ChristianNumModel findChristianNumbers();
    ChristianModel findByIdChristian(@Param("idCristiano") Integer idCristiano);
    ChristianDetailModel findByIdChristianDetail(@Param("idCristiano") Integer idCristiano);
    void registerChristian(ChristianModel christianModel);
    void updateChristian(ChristianModel christianModel);
    void deleteChristian(@Param("idCristiano") Integer idCristiano);

}
