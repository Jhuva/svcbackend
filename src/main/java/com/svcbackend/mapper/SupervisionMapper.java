package com.svcbackend.mapper;

import com.svcbackend.model.SupervisionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SupervisionMapper {
    List<SupervisionModel> findAllSupervision();
    SupervisionModel findByIdSupervision(@Param("idSupervision") Integer idSupervision);
    void registerSupervision(SupervisionModel supervisionModel);
    void updateSupervision(SupervisionModel supervisionModel);
    void deleteSupervision(@Param("idSupervision") Integer idSupervision);
}
