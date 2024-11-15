package com.svcbackend.mapper;

import com.svcbackend.model.SectorMinistriesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectorMinistriesMapper {
    List<SectorMinistriesModel> findAllSectorMinistries();
    SectorMinistriesModel findByIdSectorMinistries(@Param("idSectorMinisterio") Integer idSector_ministerio);
    void registerSectorMinistries(SectorMinistriesModel sectorMinistriesModel);
    void updateSectorMinistries(SectorMinistriesModel sectorMinistriesModel);
    void deleteSectorMinistries(@Param("idSectorMinisterio") Integer idSector_ministerio);
}
