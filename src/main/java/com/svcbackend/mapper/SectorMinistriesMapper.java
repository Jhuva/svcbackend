package com.svcbackend.mapper;

import com.svcbackend.model.SectorMinistriesAllModel;
import com.svcbackend.model.SectorMinistriesDetailModel;
import com.svcbackend.model.SectorMinistriesModel;
import com.svcbackend.model.SectorMinistriesNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectorMinistriesMapper {
    List<SectorMinistriesModel> findAllSectorMinistries();
    List<SectorMinistriesAllModel> findAllTableSectorMinistries();
    SectorMinistriesNumModel findSectorMinistriesNumbers();
    SectorMinistriesModel findByIdSectorMinistries(@Param("idSectorMinisterio") Integer idSector_ministerio);
    SectorMinistriesDetailModel findByIdSectorMinistriesDetail(@Param("idSectorMinisterio") Integer idSector_ministerio);
    void registerSectorMinistries(SectorMinistriesModel sectorMinistriesModel);
    void updateSectorMinistries(SectorMinistriesModel sectorMinistriesModel);
    void deleteSectorMinistries(@Param("idSectorMinisterio") Integer idSector_ministerio);
}
