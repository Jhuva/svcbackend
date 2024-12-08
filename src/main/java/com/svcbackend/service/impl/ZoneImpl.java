package com.svcbackend.service.impl;

import com.svcbackend.dto.SectorMinistriesAllDTO;
import com.svcbackend.dto.ZoneAllDTO;
import com.svcbackend.dto.ZoneDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.ZoneMapper;
import com.svcbackend.model.*;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ZoneService;
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
public class ZoneImpl implements ZoneService {

    private final ZoneMapper zoneMapper;

    @Override
    public GenericResponse<Object> findAllZone() throws GenericException {
        log.info("Obteniendo las Zonas...");
        try {
            List<ZoneModel> listZone = zoneMapper.findAllZone();
            List<ZoneDTO> listZoneDTO = new ArrayList<>();
            if(listZone != null && !listZone.isEmpty()) {
                listZone.forEach(zone -> {
                    ZoneDTO zoneDTO = fixSpacesCampsZone(zone);
                    listZoneDTO.add(zoneDTO);
                });
                log.info("Zonas obtenidos");
                return new GenericResponse<>(true, listZoneDTO, "Zonas obtenidos");
            } else {
                log.info("No se cuenta con las zonas");
                return new GenericResponse<>(false, "No hay lista de Zonas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findAllTableZone() throws GenericException {
        log.info("Obteniendo la lista de zonas...");
        try {
            List<ZoneAllModel> listAllZone = zoneMapper.findAllTableZone();
            List<ZoneAllDTO> listAllZoneDTO = new ArrayList<>();
            if(listAllZone != null && !listAllZone.isEmpty()) {
                listAllZone.forEach(zone -> {
                    ZoneAllDTO sectorMinistriesAllDTO = fixSpacesCampsTableZone(zone);
                    listAllZoneDTO.add(sectorMinistriesAllDTO);
                });
                log.info("Lista de Zone obtenidos");
                return new GenericResponse<>(true, listAllZoneDTO, "Lista de Zonas obtenidos");
            } else {
                log.info("No se cuenta con Zone");
                return new GenericResponse<>(false, "No hay lista de Zonas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findZoneNumbers() throws GenericException {
        log.info("Obteniendo los numeros de zonas");
        try {
            ZoneNumModel zoneRes = zoneMapper.findZoneNumbers();
            if(zoneRes != null) {
                log.info("Numeros de Zonas obtenidos");
                return new GenericResponse<>(true, zoneRes, "Números Zonas obtenidos");
            } else {
                log.info("No se cuenta con Números de Zonas");
                return new GenericResponse<>(false, "No hay números de Zonas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdZone(Integer idZone) throws GenericException {
        log.info("Obteniendo la zona {}...", idZone);
        try {
            ZoneModel zoneRes = zoneMapper.findByIdZone(idZone);
            if(zoneRes != null) {
                ZoneDTO zoneDTO = fixSpacesCampsZone(zoneRes);
                log.info("Zona {}", idZone);
                return new GenericResponse<>(true, zoneDTO, "zona " + idZone + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente zona {}", idZone);
                return new GenericResponse<>(false, "No se cuenta con la zona " + idZone);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdZoneDetail(Integer idZone) throws GenericException {
        log.info("Obteniendo el detalle de Zona {}...", idZone);
        try {
            ZoneDetailModel zoneRes = zoneMapper.findByIdZoneDetail(idZone);
            if(zoneRes != null) {
                log.info("Detalle del Zona {}", idZone);
                return new GenericResponse<>( true, zoneRes, "Zona " + idZone + " obtenido.");
            } else {
                return new GenericResponse<>(false, "Zona no existe " + idZone);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerZone(ZoneModel zoneModel) {
        log.info("Registrando la Zona {}... ", zoneModel.getIdZona());
        try {
            ZoneModel idZone = zoneMapper.findByIdZone(zoneModel.getIdZona());
            if(idZone != null) {
                log.error("Error al guardar la Zona. La Zona {} ya existe.", zoneModel.getIdZona());
                return new GenericResponse<>(false, "Error al guardar la Zona " + zoneModel.getNombre() + " ya existe.");
            } else {
                log.info("Se ha registrado el zona {}.", zoneModel.getIdZona());
                zoneMapper.registerZone(zoneModel);
                return new GenericResponse<>(true, "Se ha registrado la Zona " + zoneModel.getNombre());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar la Zona " + zoneModel.getNombre());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateZone(ZoneModel zoneModel) {
        log.info("Actualizando la Zona {}... ", zoneModel.getIdZona());
        try {
            if(zoneModel.getIdZona() != null) {
                ZoneModel idZona = zoneMapper.findByIdZone(zoneModel.getIdZona());
                if(idZona != null) {
                    zoneMapper.updateZone(zoneModel);
                    return new GenericResponse<>(true, "Zona " + zoneModel.getNombre() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar la Zona {}. No existe", zoneModel.getIdZona());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Zona " + zoneModel.getNombre() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. La Zona no debe ser nulo");
                return new GenericResponse<>(false, "Id de la Zona es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar la Zona " + zoneModel.getNombre());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteZone(Integer idZone) {
        log.info("Eliminando Zona {}...", idZone);
        try {
            ZoneModel zoneRes = zoneMapper.findByIdZone(idZone);
            if(zoneRes.getIdZona() != null) {
                zoneMapper.deleteZone(idZone);
                log.info("Zona eliminado {}", idZone);
                return new GenericResponse<>(true, "Zona " + zoneRes.getNombre() + " eliminado.");
            } else {
                log.info("No se cuenta con la siguiente Zona {}", idZone);
                return new GenericResponse<>(false, "No se cuenta con la Zona " + zoneRes.getNombre());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar la Zona " + idZone);
        }
    }

    private ZoneAllDTO fixSpacesCampsTableZone(ZoneAllModel zoneAllModel) {
        ZoneAllDTO zoneAllDTO = new ZoneAllDTO();
        BeanUtils.copyProperties(zoneAllModel, zoneAllDTO);
        if(zoneAllDTO.getIdZona() != null) {
            zoneAllDTO.setIdZona(zoneAllModel.getIdZona());
        }
        if(zoneAllDTO.getNombre() != null) {
            zoneAllDTO.setNombre(zoneAllModel.getNombre().trim());
        }
        if(zoneAllDTO.getNombrePsZonal() != null) {
            zoneAllDTO.setNombrePsZonal(zoneAllModel.getNombrePsZonal().trim());
        }
        if(zoneAllDTO.getCantSectores() != null) {
            zoneAllDTO.setCantSectores(zoneAllModel.getCantSectores());
        }
        if(zoneAllDTO.getCantSupervisiones() != null) {
            zoneAllDTO.setCantSupervisiones(zoneAllModel.getCantSupervisiones());
        }
        if(zoneAllDTO.getCantCelulas() != null) {
            zoneAllDTO.setCantCelulas(zoneAllModel.getCantCelulas());
        }
        if(zoneAllDTO.getMiembros() != null) {
            zoneAllDTO.setMiembros(zoneAllModel.getMiembros());
        }
        if(zoneAllDTO.getAsistentes() != null) {
            zoneAllDTO.setAsistentes(zoneAllModel.getAsistentes());
        }
        if(zoneAllDTO.getTotal() != null) {
            zoneAllDTO.setTotal(zoneAllModel.getTotal());
        }
        return zoneAllDTO;
    }

    private ZoneDTO fixSpacesCampsZone(ZoneModel zoneModel) {
        ZoneDTO zoneDTO = new ZoneDTO();
        BeanUtils.copyProperties(zoneModel, zoneDTO);
        if(zoneDTO.getIdZona() != null) {
            zoneDTO.setIdZona(zoneModel.getIdZona());
        }
        if(zoneDTO.getNombre() != null) {
            zoneDTO.setNombre(zoneModel.getNombre().trim());
        }
        if(zoneDTO.getNombrePsZonal() != null) {
            zoneDTO.setNombrePsZonal(zoneModel.getNombrePsZonal().trim());
        }
        return zoneDTO;
    }

}
