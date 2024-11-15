package com.svcbackend.service.impl;

import com.svcbackend.dto.ZoneDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.ZoneMapper;
import com.svcbackend.model.ZoneModel;
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
