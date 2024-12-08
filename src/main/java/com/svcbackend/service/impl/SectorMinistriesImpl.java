package com.svcbackend.service.impl;

import com.svcbackend.dto.SectorMinistriesAllDTO;
import com.svcbackend.dto.SectorMinistriesDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.SectorMinistriesMapper;
import com.svcbackend.model.*;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.SectorMinistriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SectorMinistriesImpl implements SectorMinistriesService {

    private final SectorMinistriesMapper sectorMinistriesMapper;

    @Override
    public GenericResponse<Object> findAllMinistriesService() throws GenericException {
        log.info("Obteniendo los Sectores o Ministerios...");
        try {
            List<SectorMinistriesModel> listSectorMinistries = sectorMinistriesMapper.findAllSectorMinistries();
            List<SectorMinistriesDTO> listSectorMinistriesDTO = new ArrayList<>();
            if(listSectorMinistries != null && !listSectorMinistries.isEmpty()) {
                listSectorMinistries.forEach(sectorMinistries -> {
                    SectorMinistriesDTO sectorMinistriesDTO = fixSpacesCampsSectorMinistries(sectorMinistries);
                    listSectorMinistriesDTO.add(sectorMinistriesDTO);
                });
                log.info("Sectores o Ministerios obtenidos");
                return new GenericResponse<>(true, listSectorMinistriesDTO, "Sectores o Ministerios obtenidos");
            } else {
                log.info("No se cuenta con Sectores o Ministerios");
                return new GenericResponse<>(false, "No hay Sectores o Ministerios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findAllTableSectorMinistries() throws GenericException {
        log.info("Obteniendo la lista de sectores y ministerios...");
        try {
            List<SectorMinistriesAllModel> listAllSectorMinistries = sectorMinistriesMapper.findAllTableSectorMinistries();
            List<SectorMinistriesAllDTO> listAllSectorMinistriesDTO = new ArrayList<>();
            if(listAllSectorMinistries != null && !listAllSectorMinistries.isEmpty()) {
                listAllSectorMinistries.forEach(sectorMinistries -> {
                    SectorMinistriesAllDTO sectorMinistriesAllDTO = fixSpacesCampsTableSectorMinistries(sectorMinistries);
                    listAllSectorMinistriesDTO.add(sectorMinistriesAllDTO);
                });
                log.info("Lista de Sectores y Ministerios obtenidos");
                return new GenericResponse<>(true, listAllSectorMinistriesDTO, "Lista de Sectores y Ministerios obtenidos");
            } else {
                log.info("No se cuenta con Sectores y Ministerios");
                return new GenericResponse<>(false, "No hay lista de Sectores y Ministerios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findSectorMinistriesNumbers() throws GenericException {
        log.info("Obteniendo los numeros de sectores o ministerios");
        try {
            SectorMinistriesNumModel sectorMinistriesRes = sectorMinistriesMapper.findSectorMinistriesNumbers();
            if(sectorMinistriesRes != null) {
                log.info("Numeros de Sectores y Ministerios obtenidos");
                return new GenericResponse<>(true, sectorMinistriesRes, "Números sectores o ministerios obtenidos");
            } else {
                log.info("No se cuenta con Números de Sectores o ministerios");
                return new GenericResponse<>(false, "No hay números de Sectores y Ministerios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdMinistriesService(Integer idSector_ministerio) throws GenericException {
        log.info("Obteniendo Sectores o Ministerios {}...", idSector_ministerio);
        try {
            SectorMinistriesModel sectorMinistriesRes = sectorMinistriesMapper.findByIdSectorMinistries(idSector_ministerio);
            if(sectorMinistriesRes != null) {
                SectorMinistriesDTO sectorMinistriesDTO = fixSpacesCampsSectorMinistries(sectorMinistriesRes);
                log.info("Sectores o Ministerios {}", idSector_ministerio);
                return new GenericResponse<>(true, sectorMinistriesDTO, "Sectores o Ministerios " + idSector_ministerio + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Sectores o Ministerios {}", idSector_ministerio);
                return new GenericResponse<>(false, "No se cuenta con el Sectores o Ministerios " + idSector_ministerio);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdSectorMinistriesDetail(Integer idSector_ministerio) throws GenericException {
        log.info("Obteniendo el detalle de Sector o Ministerio {}...", idSector_ministerio);
        try {
            SectorMinistriesDetailModel sectorMinistriesRes = sectorMinistriesMapper.findByIdSectorMinistriesDetail(idSector_ministerio);
            if(sectorMinistriesRes != null) {
                log.info("Detalle del Sector o Ministerio {}", idSector_ministerio);
                return new GenericResponse<>( true, sectorMinistriesRes, "Sector o Ministerio " + idSector_ministerio + " obtenido.");
            } else {
                return new GenericResponse<>(false, "Sector o Ministerio no existe " + idSector_ministerio);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerMinistriesService(SectorMinistriesModel sectorMinistriesModel) {
        log.info("Registrando el Sector o Ministerio {}... ", sectorMinistriesModel.getIdSectorMinisterio());
        try {
            SectorMinistriesModel idSectorMinistries = sectorMinistriesMapper.findByIdSectorMinistries(sectorMinistriesModel.getIdSectorMinisterio());
            if(idSectorMinistries != null) {
                log.error("Error al guardar el Sector o Ministerio. El Sector o Ministerio {} ya existe.", sectorMinistriesModel.getIdSectorMinisterio());
                return new GenericResponse<>(false, "Error al guardar el Sector o Ministerio " + sectorMinistriesModel.getNombre() + " ya existe.");
            } else {
                log.info("Se ha registrado el Sector o Ministerio {}.", sectorMinistriesModel.getIdSectorMinisterio());
                sectorMinistriesMapper.registerSectorMinistries(sectorMinistriesModel);
                return new GenericResponse<>(true, "Se ha registrado el Sector o Ministerio " + sectorMinistriesModel.getNombre());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Sector o Ministerio " + sectorMinistriesModel.getNombre());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateMinistriesService(SectorMinistriesModel sectorMinistriesModel) {
        log.info("Actualizando el Sector o Ministerio {}... ", sectorMinistriesModel.getIdSectorMinisterio());
        try {
            if(sectorMinistriesModel.getIdSectorMinisterio() != null) {
                SectorMinistriesModel idSectorMinistries = sectorMinistriesMapper.findByIdSectorMinistries(sectorMinistriesModel.getIdSectorMinisterio());
                if(idSectorMinistries != null) {
                    sectorMinistriesMapper.updateSectorMinistries(sectorMinistriesModel);
                    return new GenericResponse<>(true, "Sector o Ministerio " + sectorMinistriesModel.getNombre() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Sector o Ministerio {}. No existe", sectorMinistriesModel.getIdSectorMinisterio());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Sector o Ministerio " + sectorMinistriesModel.getNombre() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Sector o Ministerio no debe ser nulo");
                return new GenericResponse<>(false, "Id del Sector o Ministerio es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Sector o Ministerio " + sectorMinistriesModel.getNombre());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteMinistriesService(Integer idSector_ministerio) {
        log.info("Eliminando Sectores o Ministerios {}...", idSector_ministerio);
        try {
            SectorMinistriesModel sectorMinistriesRes = sectorMinistriesMapper.findByIdSectorMinistries(idSector_ministerio);
            if(sectorMinistriesRes.getIdSectorMinisterio() != null) {
                sectorMinistriesMapper.deleteSectorMinistries(idSector_ministerio);
                log.info("Sectores o Ministerios eliminado {}", idSector_ministerio);
                return new GenericResponse<>(true, "Sectores o Ministerios " + sectorMinistriesRes.getNombre() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Sectores o Ministerios {}", idSector_ministerio);
                return new GenericResponse<>(false, "No se cuenta con el Sectores o Ministerios " + sectorMinistriesRes.getNombre());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Sectores o Ministerios " + idSector_ministerio);
        }
    }

    private SectorMinistriesAllDTO fixSpacesCampsTableSectorMinistries(SectorMinistriesAllModel sectorMinistriesAllModel) {
        SectorMinistriesAllDTO sectorMinistriesAllDTO = new SectorMinistriesAllDTO();
        BeanUtils.copyProperties(sectorMinistriesAllModel, sectorMinistriesAllDTO);
        if(sectorMinistriesAllDTO.getIdZona() != null) {
            sectorMinistriesAllDTO.setIdZona(sectorMinistriesAllModel.getIdZona());
        }
        if(sectorMinistriesAllDTO.getIdSectorMinisterio() != null) {
            sectorMinistriesAllDTO.setIdSectorMinisterio(sectorMinistriesAllModel.getIdSectorMinisterio());
        }
        if(sectorMinistriesAllDTO.getNombre() != null) {
            sectorMinistriesAllDTO.setNombre(sectorMinistriesAllModel.getNombre().trim());
        }
        if(sectorMinistriesAllDTO.getNombreColPast() != null) {
            sectorMinistriesAllDTO.setNombreColPast(sectorMinistriesAllModel.getNombreColPast().trim());
        }
        if(sectorMinistriesAllDTO.getNombreZona() != null) {
            sectorMinistriesAllDTO.setNombreZona(sectorMinistriesAllModel.getNombreZona().trim());
        }
        if(sectorMinistriesAllDTO.getCantSupervisiones() != null) {
            sectorMinistriesAllDTO.setCantSupervisiones(sectorMinistriesAllModel.getCantSupervisiones());
        }
        if(sectorMinistriesAllDTO.getCantCelulas() != null) {
            sectorMinistriesAllDTO.setCantCelulas(sectorMinistriesAllModel.getCantCelulas());
        }
        if(sectorMinistriesAllDTO.getMiembros() != null) {
            sectorMinistriesAllDTO.setMiembros(sectorMinistriesAllModel.getMiembros());
        }
        if(sectorMinistriesAllDTO.getAsistentes() != null) {
            sectorMinistriesAllDTO.setAsistentes(sectorMinistriesAllModel.getAsistentes());
        }
        if(sectorMinistriesAllDTO.getTotal() != null) {
            sectorMinistriesAllDTO.setTotal(sectorMinistriesAllModel.getTotal());
        }
        return sectorMinistriesAllDTO;
    }

    private SectorMinistriesDTO fixSpacesCampsSectorMinistries(SectorMinistriesModel sectorMinistriesModel) {
        SectorMinistriesDTO sectorMinistriesDTO = new SectorMinistriesDTO();
        BeanUtils.copyProperties(sectorMinistriesModel, sectorMinistriesDTO);
        if(sectorMinistriesDTO.getIdSectorMinisterio() != null) {
            sectorMinistriesDTO.setIdSectorMinisterio(sectorMinistriesModel.getIdSectorMinisterio());
        }
        if(sectorMinistriesDTO.getIdZona() != null) {
            sectorMinistriesDTO.setIdZona(sectorMinistriesModel.getIdZona());
        }
        if(sectorMinistriesDTO.getNombre() != null) {
            sectorMinistriesDTO.setNombre(sectorMinistriesModel.getNombre().trim());
        }
        if(sectorMinistriesDTO.getNombreColPast() != null) {
            sectorMinistriesDTO.setNombreColPast(sectorMinistriesModel.getNombreColPast().trim());
        }
        return sectorMinistriesDTO;
    }

}
