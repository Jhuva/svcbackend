package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.SectorMinistriesModel;
import com.svcbackend.response.GenericResponse;

public interface SectorMinistriesService {
    GenericResponse<Object> findAllMinistriesService() throws GenericException;
    GenericResponse<Object> findByIdMinistriesService(Integer idSector_ministerio) throws GenericException;
    GenericResponse<Object> registerMinistriesService(SectorMinistriesModel sectorMinistries);
    GenericResponse<Object> updateMinistriesService(SectorMinistriesModel sectorMinistries);
    GenericResponse<Object> deleteMinistriesService(Integer idSector_ministerio);
}
