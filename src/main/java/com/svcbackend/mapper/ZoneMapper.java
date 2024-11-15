package com.svcbackend.mapper;

import com.svcbackend.model.ZoneModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZoneMapper {

    List<ZoneModel> findAllZone();
    ZoneModel findByIdZone(@Param("idZona") Integer idZona);
    void registerZone(ZoneModel zoneModel);
    void updateZone(ZoneModel zoneModel);
    void deleteZone(@Param("idZona") Integer idZona);

}
