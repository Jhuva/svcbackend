package com.svcbackend.service.impl;

import com.svcbackend.dto.ServiceAreaDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.ServiceAreaMapper;
import com.svcbackend.model.ServiceAreaModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ServiceAreaService;
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
public class ServiceAreaImpl implements ServiceAreaService {

    private final ServiceAreaMapper serviceAreaMapper;

    @Override
    public GenericResponse<Object> findAllServiceArea() throws GenericException {
        log.info("Obteniendo las Areas de servicio...");
        try {
            List<ServiceAreaModel> listServiceArea = serviceAreaMapper.findAllServiceArea();
            List<ServiceAreaDTO> listServiceAreaDTO = new ArrayList<>();
            if(listServiceArea != null && !listServiceArea.isEmpty()) {
                listServiceArea.forEach(serviceArea -> {
                    ServiceAreaDTO serviceAreaDTO = fixSpacesCampsServiceArea(serviceArea);
                    listServiceAreaDTO.add(serviceAreaDTO);
                });
                log.info("Area de servicio obtenidos");
                return new GenericResponse<>(true, listServiceAreaDTO, "Area de servicio obtenidos");
            } else {
                log.info("No se cuenta con Areas de servicio");
                return new GenericResponse<>(false, "No hay Áreas de servicio");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdServiceArea(Integer idServiceArea) throws GenericException {
        log.info("Obteniendo Area de servicio {}...", idServiceArea);
        try {
            ServiceAreaModel serviceAreaRes = serviceAreaMapper.findByIdServiceArea(idServiceArea);
            if(serviceAreaRes != null) {
                ServiceAreaDTO serviceAreaDTO = fixSpacesCampsServiceArea(serviceAreaRes);
                log.info("Area de servicio {}", idServiceArea);
                return new GenericResponse<>(true, serviceAreaDTO, "Área de servicio " + idServiceArea + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Area de servicio {}", idServiceArea);
                return new GenericResponse<>(false, "No se cuenta con el Área de servicio " + idServiceArea);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerServiceArea(ServiceAreaModel serviceAreaModel) {
        log.info("Registrando el Area de servicio {}... ", serviceAreaModel.getIdAreaServicio());
        try {
            ServiceAreaModel idServiceArea = serviceAreaMapper.findByIdServiceArea(serviceAreaModel.getIdAreaServicio());
            if(idServiceArea != null) {
                log.error("Error al guardar el Area de servicio. El Area de servicio {} ya existe.", serviceAreaModel.getIdAreaServicio());
                return new GenericResponse<>(false, "Error al guardar el Área de servicio " + serviceAreaModel.getAreaServicio() + " ya existe.");
            } else {
                log.info("Se ha registrado el Area de servicio {}.", serviceAreaModel.getIdAreaServicio());
                serviceAreaMapper.registerServiceArea(serviceAreaModel);
                return new GenericResponse<>(true, "Se ha registrado el Área de servicio " + serviceAreaModel.getAreaServicio());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Área de servicio " + serviceAreaModel.getAreaServicio());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateServiceArea(ServiceAreaModel serviceAreaModel) {
        log.info("Actualizando el Area de servicio {}... ", serviceAreaModel.getIdAreaServicio());
        try {
            if(serviceAreaModel.getIdAreaServicio() != null) {
                ServiceAreaModel idServiceArea = serviceAreaMapper.findByIdServiceArea(serviceAreaModel.getIdAreaServicio());
                if(idServiceArea != null) {
                    serviceAreaMapper.updateServiceArea(serviceAreaModel);
                    return new GenericResponse<>(true, "Área de servicio " + serviceAreaModel.getAreaServicio() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Area de servicio {}. No existe", serviceAreaModel.getIdAreaServicio());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Área de servicio " + serviceAreaModel.getAreaServicio() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Area de servicio no debe ser nulo");
                return new GenericResponse<>(false, "Id del Área de servicio es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Área de servicio " + serviceAreaModel.getAreaServicio());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteServiceArea(Integer idServiceArea) {
        log.info("Eliminando Area de servicio {}...", idServiceArea);
        try {
            ServiceAreaModel serviceAreaRes = serviceAreaMapper.findByIdServiceArea(idServiceArea);
            if(serviceAreaRes.getIdAreaServicio() != null) {
                serviceAreaMapper.deleteServiceArea(idServiceArea);
                log.info("Area de servicio eliminado {}", idServiceArea);
                return new GenericResponse<>(true, "Área de servicio " + serviceAreaRes.getAreaServicio() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Area de servicio {}", idServiceArea);
                return new GenericResponse<>(false, "No se cuenta con el Área de servicio " + serviceAreaRes.getAreaServicio());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Área de servicio " + idServiceArea);
        }
    }

    private ServiceAreaDTO fixSpacesCampsServiceArea(ServiceAreaModel serviceAreaModel) {
        ServiceAreaDTO serviceAreaDTO = new ServiceAreaDTO();
        BeanUtils.copyProperties(serviceAreaModel, serviceAreaDTO);
        if(serviceAreaDTO.getIdAreaServicio() != null) {
            serviceAreaDTO.setIdAreaServicio(serviceAreaModel.getIdAreaServicio());
        }
        if(serviceAreaDTO.getAreaServicio() != null) {
            serviceAreaDTO.setAreaServicio(serviceAreaModel.getAreaServicio().trim());
        }
        return serviceAreaDTO;
    }

}
