package com.svcbackend.service.impl;

import com.svcbackend.dto.LeaderStateDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.LeaderStateMapper;
import com.svcbackend.model.LeaderStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.LeaderStateService;
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
public class LeaderStateImpl implements LeaderStateService {

    private final LeaderStateMapper leaderStateMapper;

    @Override
    public GenericResponse<Object> findAllLeaderState() throws GenericException {
        log.info("Obteniendo los Estados de lideres...");
        try {
            List<LeaderStateModel> listLeaderState = leaderStateMapper.findAllLeaderState();
            List<LeaderStateDTO> listLeaderStateDTO = new ArrayList<>();
            if(listLeaderState != null && !listLeaderState.isEmpty()) {
                listLeaderState.forEach(leaderState -> {
                    LeaderStateDTO leaderStateDTO = fixSpacesCampsLeaderState(leaderState);
                    listLeaderStateDTO.add(leaderStateDTO);
                });
                log.info("Estados de lideres obtenidos");
                return new GenericResponse<>(true, listLeaderStateDTO, "Estados de líderes obtenidos");
            } else {
                log.info("No se cuenta con Estados de Lideres");
                return new GenericResponse<>(false, "No hay lista de Estados de líderes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdLeaderState(Integer idLeaderState) throws GenericException {
        log.info("Obteniendo el estado de lider {}...", idLeaderState);
        try {
            LeaderStateModel leaderStateRes = leaderStateMapper.findByIdLeaderState(idLeaderState);
            if(leaderStateRes != null) {
                LeaderStateDTO leaderStateDTO = fixSpacesCampsLeaderState(leaderStateRes);
                log.info("Estado Lider {}", idLeaderState);
                return new GenericResponse<>(true, leaderStateDTO, "Estado lider " + idLeaderState + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Estado lider {}", idLeaderState);
                return new GenericResponse<>(false, "No se cuenta con el Estado lider " + idLeaderState);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerLeaderState(LeaderStateModel leaderStateModel) {
        log.info("Registrando el Estado lider {}... ", leaderStateModel.getIdEstadoLider());
        try {
            LeaderStateModel idLeaderState = leaderStateMapper.findByIdLeaderState(leaderStateModel.getIdEstadoLider());
            if(idLeaderState != null) {
                log.error("Error al guardar el Estado lider. El Estado lider {} ya existe.", leaderStateModel.getIdEstadoLider());
                return new GenericResponse<>(false, "Error al guardar el Estado lider " + leaderStateModel.getEstadoLider() + " ya existe.");
            } else {
                log.info("Se ha registrado el Estado lider {}.", leaderStateModel.getIdEstadoLider());
                leaderStateMapper.registerLeaderState(leaderStateModel);
                return new GenericResponse<>(true, "Se ha registrado el Estado lider " + leaderStateModel.getEstadoLider());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Estado lider " + leaderStateModel.getEstadoLider());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateLeaderState(LeaderStateModel leaderStateModel) {
        log.info("Actualizando el Estado lider {}... ", leaderStateModel.getIdEstadoLider());
        try {
            if(leaderStateModel.getIdEstadoLider() != null) {
                LeaderStateModel idLeaderState = leaderStateMapper.findByIdLeaderState(leaderStateModel.getIdEstadoLider());
                if(idLeaderState != null) {
                    leaderStateMapper.updateLeaderState(leaderStateModel);
                    return new GenericResponse<>(true, "Estado lider " + leaderStateModel.getEstadoLider() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Estado lider {}. No existe", leaderStateModel.getIdEstadoLider());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Estado lider " + leaderStateModel.getEstadoLider() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Estado lider no debe ser nulo");
                return new GenericResponse<>(false, "Id del Estado lider es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Estado lider " + leaderStateModel.getEstadoLider());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteLeaderState(Integer idLeaderState) {
        log.info("Eliminando Estado lider {}...", idLeaderState);
        try {
            LeaderStateModel leaderStateRes = leaderStateMapper.findByIdLeaderState(idLeaderState);
            if(leaderStateRes.getIdEstadoLider() != null) {
                leaderStateMapper.deleteLeaderState(idLeaderState);
                log.info("Estado lider eliminado {}", idLeaderState);
                return new GenericResponse<>(true, "Estado lider " + leaderStateRes.getEstadoLider() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Estado lider {}", idLeaderState);
                return new GenericResponse<>(false, "No se cuenta con el Estado lider " + leaderStateRes.getEstadoLider());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Estado lider " + idLeaderState);
        }
    }

    private LeaderStateDTO fixSpacesCampsLeaderState(LeaderStateModel leaderStateModel) {
        LeaderStateDTO leaderStateDTO = new LeaderStateDTO();
        BeanUtils.copyProperties(leaderStateModel, leaderStateDTO);
        if(leaderStateDTO.getIdEstadoLider() != null) {
            leaderStateDTO.setIdEstadoLider(leaderStateModel.getIdEstadoLider());
        }
        if(leaderStateDTO.getEstadoLider() != null) {
            leaderStateDTO.setEstadoLider(leaderStateModel.getEstadoLider().trim());
        }
        return leaderStateDTO;
    }

}
