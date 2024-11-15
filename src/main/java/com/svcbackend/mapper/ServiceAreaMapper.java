package com.svcbackend.mapper;

import com.svcbackend.model.ServiceAreaModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceAreaMapper {

    List<ServiceAreaModel> findAllServiceArea();
    ServiceAreaModel findByIdServiceArea(@Param("idAreaServicio") Integer idArea_servicio);
    void registerServiceArea(ServiceAreaModel serviceAreaModel);
    void updateServiceArea(ServiceAreaModel serviceAreaModel);
    void deleteServiceArea(@Param("idAreaServicio") Integer idArea_servicio);

}
