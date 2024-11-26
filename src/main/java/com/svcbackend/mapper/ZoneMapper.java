package com.svcbackend.mapper;

import com.svcbackend.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZoneMapper {

    List<ZoneModel> findAllZone();
    List<ZoneAllModel> findAllTableZone();
    ZoneNumModel findZoneNumbers();
    ZoneModel findByIdZone(@Param("idZona") Integer idZona);
    ZoneDetailModel findByIdZoneDetail(@Param("idZona") Integer idZona);
    void registerZone(ZoneModel zoneModel);
    void updateZone(ZoneModel zoneModel);
    void deleteZone(@Param("idZona") Integer idZona);

}
