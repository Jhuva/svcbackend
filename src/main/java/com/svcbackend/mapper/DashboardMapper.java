package com.svcbackend.mapper;

import com.svcbackend.model.DashDetailZonesModel;
import com.svcbackend.model.DashListZonesModel;
import com.svcbackend.model.DashNumbersDonutsModel;
import com.svcbackend.model.DashNumbersModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardMapper {
    DashNumbersModel findDashNumbers();
    DashNumbersDonutsModel findDashDonuts();
    List<DashListZonesModel> findDashListZones();
    DashDetailZonesModel findDashDetailZones(@Param("idSectorMinisterio") Integer idSectorMinisterio);
}
