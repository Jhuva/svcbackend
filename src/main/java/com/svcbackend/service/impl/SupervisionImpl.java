package com.svcbackend.service.impl;

import com.svcbackend.dto.SupervisionAllDTO;
import com.svcbackend.dto.SupervisionDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.SupervisionMapper;
import com.svcbackend.model.*;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.SupervisionService;
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
public class SupervisionImpl implements SupervisionService {

    private final SupervisionMapper supervisionMapper;

    @Override
    public GenericResponse<Object> findAllSupervisionService() throws GenericException {
        log.info("Obteniendo las Supervisiones...");
        try {
            List<SupervisionModel> listSupervision = supervisionMapper.findAllSupervision();
            List<SupervisionDTO> listSupervisionDTO = new ArrayList<>();
            if(listSupervision != null && !listSupervision.isEmpty()) {
                listSupervision.forEach(supervision -> {
                    SupervisionDTO supervisionDTO = fixSpacesCampsSupervision(supervision);
                    listSupervisionDTO.add(supervisionDTO);
                });
                log.info("Supervisiones obtenidas");
                return new GenericResponse<>(true, listSupervisionDTO, "Supervisiones obtenidas");
            } else {
                log.info("No se cuenta con Supervisiones");
                return new GenericResponse<>(false, "No hay Supervisiones");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findAllTableSupervision() throws GenericException {
        log.info("Obteniendo la lista de supervisiones...");
        try {
            List<SupervisionAllModel> listAllSupervision = supervisionMapper.findAllTableSupervision();
            List<SupervisionAllDTO> listAllSupervisionDTO = new ArrayList<>();
            if(listAllSupervision != null && !listAllSupervision.isEmpty()) {
                listAllSupervision.forEach(supervision -> {
                    SupervisionAllDTO supervisionAllDTO = fixSpacesCampsTableSupervision(supervision);
                    listAllSupervisionDTO.add(supervisionAllDTO);
                });
                log.info("Lista de supervisiones obtenidos");
                return new GenericResponse<>(true, listAllSupervisionDTO, "Lista de Supervisiones obtenidos");
            } else {
                log.info("No se cuenta con supervisiones");
                return new GenericResponse<>(false, "No hay lista de Supervisiones");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findSupervisionNumbers() throws GenericException {
        log.info("Obteniendo los numeros de las supervisiones");
        try {
            SupervisionNumModel supervisionNumRes = supervisionMapper.findSupervisionNumbers();
            if(supervisionNumRes != null) {
                log.info("Numeros de supervisiones obtenidos");
                return new GenericResponse<>(true, supervisionNumRes, "Números supervisiones obtenidos");
            } else {
                log.info("No se cuenta con Numeros de supervisiones");
                return new GenericResponse<>(false, "No hay números de supervisiones");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdSupervisionService(Integer idSupervision) throws GenericException {
        log.info("Obteniendo Supervision {}...", idSupervision);
        try {
            SupervisionModel supervisionRes = supervisionMapper.findByIdSupervision(idSupervision);
            if(supervisionRes != null) {
                SupervisionDTO supervisionDTO = fixSpacesCampsSupervision(supervisionRes);
                log.info("Supervision {}", idSupervision);
                return new GenericResponse<>(true, supervisionDTO, "Supervision " + idSupervision + " obtenido.");
            } else {
                log.info("No se cuenta con la siguiente Supervision {}", idSupervision);
                return new GenericResponse<>(false, "No se cuenta con la Supervision " + idSupervision);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdSupervisionDetail(Integer idSupervision) throws GenericException {
        log.info("Obteniendo el detalle de la supervision {}...", idSupervision);
        try {
            SupervisionDetailModel supervisionRes = supervisionMapper.findByIdSupervisionDetail(idSupervision);
            if(supervisionRes != null) {
                log.info("Detalle de la supervision {}", idSupervision);
                return new GenericResponse<>( true, supervisionRes, "Célula " + idSupervision + " obtenido.");
            } else {
                return new GenericResponse<>(false, "Célula no existe " + idSupervision);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerSupervisionService(SupervisionModel supervisionModel) {
        log.info("Registrando la Supervision {}... ", supervisionModel.getIdSupervision());
        try {
            SupervisionModel idSupervision = supervisionMapper.findByIdSupervision(supervisionModel.getIdSupervision());
            if(idSupervision != null) {
                log.error("Error al guardar la Supervision. La Supervision {} ya existe.", supervisionModel.getIdSupervision());
                return new GenericResponse<>(false, "Error al guardar la Supervisión " + supervisionModel.getNombreSupervision() + " ya existe.");
            } else {
                log.info("Se ha registrado la Supervision {}.", supervisionModel.getIdSupervision());
                supervisionMapper.registerSupervision(supervisionModel);
                return new GenericResponse<>(true, "Se ha registrado la supervisión " + supervisionModel.getNombreSupervision());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar la supervisión " + supervisionModel.getNombreSupervision());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateSupervisionService(SupervisionModel supervisionModel) {
        log.info("Actualizando el supervision {}... ", supervisionModel.getIdSupervision());
        try {
            if(supervisionModel.getIdSupervision() != null) {
                SupervisionModel idSupervision = supervisionMapper.findByIdSupervision(supervisionModel.getIdSupervision());
                if(idSupervision != null) {
                    supervisionMapper.updateSupervision(supervisionModel);
                    return new GenericResponse<>(true, "supervision " + supervisionModel.getNombreSupervision() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar la supervision {}. No existe", supervisionModel.getIdSupervision());
                    return new GenericResponse<>(false, "Error al momento de actualizar la supervisión " + supervisionModel.getNombreSupervision() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. La supervision no debe ser nulo");
                return new GenericResponse<>(false, "Id de la supervisión es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar la supervisión " + supervisionModel.getNombreSupervision());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteSupervisionService(Integer idSupervision) {
        log.info("Eliminando supervision {}...", idSupervision);
        try {
            SupervisionModel supervisionRes = supervisionMapper.findByIdSupervision(idSupervision);
            if(supervisionRes.getIdSupervision() != null) {
                supervisionMapper.deleteSupervision(idSupervision);
                log.info("Supervision eliminado {}", idSupervision);
                return new GenericResponse<>(true, "Supervisión " + supervisionRes.getNombreSupervision() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Supervision {}", idSupervision);
                return new GenericResponse<>(false, "No se cuenta con el supervision " + supervisionRes.getNombreSupervision());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar la supervisión " + idSupervision);
        }
    }

    private SupervisionAllDTO fixSpacesCampsTableSupervision(SupervisionAllModel supervisionAllModel) {
        SupervisionAllDTO supervisionAllDTO = new SupervisionAllDTO();
        BeanUtils.copyProperties(supervisionAllModel, supervisionAllDTO);
        if(supervisionAllDTO.getIdSupervision() != null) {
            supervisionAllDTO.setIdSupervision(supervisionAllModel.getIdSupervision());
        }
        if(supervisionAllDTO.getIdSectorMinisterio() != null) {
            supervisionAllDTO.setIdSectorMinisterio(supervisionAllModel.getIdSectorMinisterio());
        }
        if(supervisionAllDTO.getNombreSupervision() != null) {
            supervisionAllDTO.setNombreSupervision(supervisionAllModel.getNombreSupervision().trim());
        }
        if(supervisionAllDTO.getNombreSupervisor() != null) {
            supervisionAllDTO.setNombreSupervisor(supervisionAllModel.getNombreSupervisor().trim());
        }
        if(supervisionAllDTO.getSectMinisterio() != null) {
            supervisionAllDTO.setSectMinisterio(supervisionAllModel.getSectMinisterio().trim());
        }
        if(supervisionAllDTO.getNombreZona() != null) {
            supervisionAllDTO.setNombreZona(supervisionAllModel.getNombreZona().trim());
        }
        if(supervisionAllDTO.getCantCelulas() != null) {
            supervisionAllDTO.setCantCelulas(supervisionAllModel.getCantCelulas());
        }
        if(supervisionAllDTO.getMiembros() != null) {
            supervisionAllDTO.setMiembros(supervisionAllModel.getMiembros());
        }
        if(supervisionAllDTO.getAsistentes() != null) {
            supervisionAllDTO.setAsistentes(supervisionAllModel.getAsistentes());
        }
        if(supervisionAllDTO.getTotal() != null) {
            supervisionAllDTO.setTotal(supervisionAllModel.getTotal());
        }
        return supervisionAllDTO;
    }


    private SupervisionDTO fixSpacesCampsSupervision(SupervisionModel supervisionModel) {
        SupervisionDTO supervisionDTO = new SupervisionDTO();
        BeanUtils.copyProperties(supervisionModel, supervisionDTO);
        if(supervisionDTO.getIdSupervision() != null) {
            supervisionDTO.setIdSupervision(supervisionModel.getIdSupervision());
        }
        if(supervisionDTO.getIdSectorMinisterio() != null) {
            supervisionDTO.setIdSectorMinisterio(supervisionModel.getIdSectorMinisterio());
        }
        if(supervisionDTO.getNombreSupervision() != null) {
            supervisionDTO.setNombreSupervision(supervisionModel.getNombreSupervision().trim());
        }
        if(supervisionDTO.getNombreSupervisor() != null) {
            supervisionDTO.setNombreSupervisor(supervisionModel.getNombreSupervisor().trim());
        }
        return supervisionDTO;
    }

}
