package com.svcbackend.mapper;

import com.svcbackend.model.SupervisionAllModel;
import com.svcbackend.model.SupervisionDetailModel;
import com.svcbackend.model.SupervisionModel;
import com.svcbackend.model.SupervisionNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SupervisionMapper {
    List<SupervisionModel> findAllSupervision();
    List<SupervisionAllModel> findAllTableSupervision();
    SupervisionNumModel findSupervisionNumbers();
    SupervisionModel findByIdSupervision(@Param("idSupervision") Integer idSupervision);
    SupervisionDetailModel findByIdSupervisionDetail(@Param("idSupervision") Integer idSupervision);
    void registerSupervision(SupervisionModel supervisionModel);
    void updateSupervision(SupervisionModel supervisionModel);
    void deleteSupervision(@Param("idSupervision") Integer idSupervision);
}
