package com.svcbackend.service.impl;

import com.svcbackend.dto.CivilStateDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.CivilStateMapper;
import com.svcbackend.model.CivilStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CivilStateService;
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
public class CivilStateImpl implements CivilStateService {

    private final CivilStateMapper civilStateMapper;

    @Override
    public GenericResponse<Object> findAllCivilState() throws GenericException {
        log.info("Obteniendo los Estados civil...");
        try {
            List<CivilStateModel> listCivilState = civilStateMapper.findAllCivilState();
            List<CivilStateDTO> listCivilStateDTO = new ArrayList<>();
            if(listCivilState != null && !listCivilState.isEmpty()) {
                listCivilState.forEach(civilState -> {
                    CivilStateDTO civilStateDTO = fixSpacesCampsCivilState(civilState);
                    listCivilStateDTO.add(civilStateDTO);
                });
                log.info("Estados civil obtenidos");
                return new GenericResponse<>(true, listCivilStateDTO, "Estados civil obtenidos");
            } else {
                log.info("No se cuenta con Estados de Civiles");
                return new GenericResponse<>(false, "No hay lista de Estados civil");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdCivilState(Integer idCivilState) throws GenericException {
        log.info("Obteniendo el estado civil {}...", idCivilState);
        try {
            CivilStateModel civilStateRes = civilStateMapper.findByIdCivilState(idCivilState);
            if(civilStateRes != null) {
                CivilStateDTO civilStateDTO = fixSpacesCampsCivilState(civilStateRes);
                log.info("Estado Civil {}", idCivilState);
                return new GenericResponse<>(true, civilStateDTO, "Estado civil " + idCivilState + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Estado civil {}", idCivilState);
                return new GenericResponse<>(false, "No se cuenta con el Estado civil " + idCivilState);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerCivilState(CivilStateModel civilStateModel) {
        log.info("Registrando el Estado civil {}... ", civilStateModel.getIdEstadoCivil());
        try {
            CivilStateModel idCivilState = civilStateMapper.findByIdCivilState(civilStateModel.getIdEstadoCivil());
            if(idCivilState != null) {
                log.error("Error al guardar el Estado civil. El Estado civil {} ya existe.", civilStateModel.getIdEstadoCivil());
                return new GenericResponse<>(false, "Error al guardar el Estado civil " + civilStateModel.getEstadoCivil() + " ya existe.");
            } else {
                log.info("Se ha registrado el Estado civil {}.", civilStateModel.getIdEstadoCivil());
                civilStateMapper.registerCivilState(civilStateModel);
                return new GenericResponse<>(true, "Se ha registrado el Estado civil " + civilStateModel.getEstadoCivil());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Estado civil " + civilStateModel.getEstadoCivil());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateCivilState(CivilStateModel civilStateModel) {
        log.info("Actualizando el Estado civil {}... ", civilStateModel.getIdEstadoCivil());
        try {
            if(civilStateModel.getIdEstadoCivil() != null) {
                CivilStateModel idCivilState = civilStateMapper.findByIdCivilState(civilStateModel.getIdEstadoCivil());
                if(idCivilState != null) {
                    civilStateMapper.updateCivilState(civilStateModel);
                    return new GenericResponse<>(true, "Estado civil " + civilStateModel.getEstadoCivil() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Estado civil {}. No existe", civilStateModel.getIdEstadoCivil());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Estado civil " + civilStateModel.getEstadoCivil() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Estado civil no debe ser nulo");
                return new GenericResponse<>(false, "Id del Estado civil es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Estado civil " + civilStateModel.getEstadoCivil());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteCivilState(Integer idCivilState) {
        log.info("Eliminando Estado civil {}...", idCivilState);
        try {
            CivilStateModel civilStateRes = civilStateMapper.findByIdCivilState(idCivilState);
            if(civilStateRes.getIdEstadoCivil() != null) {
                civilStateMapper.deleteCivilState(idCivilState);
                log.info("Estado civil eliminado {}", idCivilState);
                return new GenericResponse<>(true, "Estado civil " + civilStateRes.getEstadoCivil() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Estado civil {}", idCivilState);
                return new GenericResponse<>(false, "No se cuenta con el Estado civil " + civilStateRes.getEstadoCivil());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Estado civil " + idCivilState);
        }
    }

    private CivilStateDTO fixSpacesCampsCivilState(CivilStateModel civilStateModel) {
        CivilStateDTO civilStateDTO = new CivilStateDTO();
        BeanUtils.copyProperties(civilStateModel, civilStateDTO);
        if(civilStateDTO.getIdEstadoCivil() != null) {
            civilStateDTO.setIdEstadoCivil(civilStateModel.getIdEstadoCivil());
        }
        if(civilStateDTO.getEstadoCivil() != null) {
            civilStateDTO.setEstadoCivil(civilStateModel.getEstadoCivil().trim());
        }
        return civilStateDTO;
    }

}
