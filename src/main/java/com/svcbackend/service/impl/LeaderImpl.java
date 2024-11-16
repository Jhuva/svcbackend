package com.svcbackend.service.impl;

import com.svcbackend.dto.LeaderAllDTO;
import com.svcbackend.dto.LeaderDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.LeaderMapper;
import com.svcbackend.model.*;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.LeaderService;
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
public class LeaderImpl implements LeaderService {

    private final LeaderMapper leaderMapper;

    @Override
    public GenericResponse<Object> findAllLeader() throws GenericException {
        log.info("Obteniendo la lista de lideres...");
        try {
            List<LeaderModel> listLeader = leaderMapper.findAllLeader();
            List<LeaderDTO> listLeaderDTO = new ArrayList<>();
            if(listLeader != null && !listLeader.isEmpty()) {
                listLeader.forEach(leader -> {
                    LeaderDTO leaderDTO = fixSpacesCampsLeader(leader);
                    listLeaderDTO.add(leaderDTO);
                });
                log.info("Lista de Lideres obtenidos");
                return new GenericResponse<>(true, listLeaderDTO, "Lista de Líderes obtenidos");
            } else {
                log.info("No se cuenta con Lideres");
                return new GenericResponse<>(false, "No hay lista de Líderes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findAllTableLeader() throws GenericException {
        log.info("Obteniendo la lista de lideres...");
        try {
            List<LeaderAllModel> listAllLeader = leaderMapper.findAllTableLeader();
            List<LeaderAllDTO> listAllLeaderDTO = new ArrayList<>();
            if(listAllLeader != null && !listAllLeader.isEmpty()) {
                listAllLeader.forEach(leader -> {
                    LeaderAllDTO leaderAllDTO = fixSpacesCampsTableLeader(leader);
                    listAllLeaderDTO.add(leaderAllDTO);
                });
                log.info("Lista de Lideres obtenidos");
                return new GenericResponse<>(true, listAllLeaderDTO, "Lista de Líderes obtenidos");
            } else {
                log.info("No se cuenta con Lideres");
                return new GenericResponse<>(false, "No hay lista de Líderes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findLeaderNumbers() throws GenericException {
        log.info("Obteniendo los numeros de lideres");
        try {
            LeaderNumModel leaderRes = leaderMapper.findLeaderNumbers();
            if(leaderRes != null) {
                log.info("Numeros de Cristianos obtenidos");
                return new GenericResponse<>(true, leaderRes, "Números líderes obtenidos");
            } else {
                log.info("No se cuenta con Numeros de lideres");
                return new GenericResponse<>(false, "No hay números de líderes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdLeader(Integer idLeader) throws GenericException {
        log.info("Obteniendo al lider {}...", idLeader);
        try {
            LeaderModel leaderRes = leaderMapper.findByIdLeader(idLeader);
            if(leaderRes != null) {
                LeaderDTO leaderDTO = fixSpacesCampsLeader(leaderRes);
                log.info("Lider {}", idLeader);
                return new GenericResponse<>(true, leaderDTO, "Líder " + idLeader + " obtenido.");
            } else {
                log.info("No se cuenta con el lider {}", idLeader);
                return new GenericResponse<>(false, "No se cuenta con el líder " + idLeader);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdLeaderDetail(Integer idLeader) throws GenericException {
        log.info("Obteniendo el detalle del lider {}...", idLeader);
        try {
            LeaderDetailModel leaderRes = leaderMapper.findByIdLeaderDetail(idLeader);
            if(leaderRes != null) {
                log.info("Detalle de lider {}", idLeader);
                return new GenericResponse<>( true, leaderRes, "Lider " + idLeader + " obtenido.");
            } else {
                return new GenericResponse<>(false, "Lider no existe " + idLeader);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerLeader(LeaderModel leaderModel) {
        log.info("Registrando a un nuevo lider {}... ", leaderModel.getIdLider());
        try {
            LeaderModel idLeader = leaderMapper.findByIdLeader(leaderModel.getIdLider());
            if(idLeader != null) {
                log.error("Error al guardar al Lider. El Lider {} ya existe.", leaderModel.getIdLider());
                return new GenericResponse<>(false, "Error al guardar al Líder " + leaderModel.getIdLider() + " ya existe.");
            } else {
                log.info("Se ha registrado al Lider {}.", leaderModel.getIdLider());
                leaderMapper.registerLeader(leaderModel);
                return new GenericResponse<>(true, "Se ha registrado al Líder " + leaderModel.getIdLider() );
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar al Líder " + leaderModel.getIdLider());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateLeader(LeaderModel leaderModel) {
        log.info("Actualizando al Lider {}... ", leaderModel.getIdLider());
        try {
            if(leaderModel.getIdLider() != null) {
                LeaderModel idLeader = leaderMapper.findByIdLeader(leaderModel.getIdLider());
                if(idLeader != null) {
                    leaderMapper.updateLeader(leaderModel);
                    return new GenericResponse<>(true, "Líder " + leaderModel.getIdLider() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar al Lider {}. No existe", leaderModel.getIdLider());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Líder " + leaderModel.getIdLider() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Lider no debe ser nulo");
                return new GenericResponse<>(false, "Id del Líder es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar al Líder " + leaderModel.getIdLider());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteLeader(Integer idLeader) {
        log.info("Eliminando Lider {}...", idLeader);
        try {
            LeaderModel leaderRes = leaderMapper.findByIdLeader(idLeader);
            if(leaderRes.getIdLider() != null) {
                leaderMapper.deleteLeader(idLeader);
                log.info("Lider eliminado {}", idLeader);
                return new GenericResponse<>(true, "Líder " + leaderRes.getIdLider() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Lider {}", idLeader);
                return new GenericResponse<>(false, "No se cuenta con el Líder " + leaderRes.getIdLider());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar al Líder " + idLeader);
        }
    }

    private LeaderAllDTO fixSpacesCampsTableLeader(LeaderAllModel leaderAllModel) {
        LeaderAllDTO leaderAllDTO = new LeaderAllDTO();
        BeanUtils.copyProperties(leaderAllModel, leaderAllDTO);
        if(leaderAllDTO.getNombres() != null) {
            leaderAllDTO.setNombres(leaderAllModel.getNombres().trim());
        }
        if(leaderAllDTO.getApellidos() != null) {
            leaderAllDTO.setApellidos(leaderAllModel.getApellidos().trim());
        }
        if(leaderAllDTO.getEstado() != null) {
            leaderAllDTO.setEstado(leaderAllModel.getEstado().trim());
        }
        if(leaderAllDTO.getAreaServicio() != null) {
            leaderAllDTO.setAreaServicio(leaderAllModel.getAreaServicio().trim());
        }
        if(leaderAllDTO.getGrado() != null) {
            leaderAllDTO.setGrado(leaderAllModel.getGrado());
        }
        if(leaderAllDTO.getLiderCelular() != null) {
            leaderAllDTO.setLiderCelular(leaderAllModel.getLiderCelular().trim());
        }
        if(leaderAllDTO.getSectorMinisterio() != null) {
            leaderAllDTO.setSectorMinisterio(leaderAllModel.getSectorMinisterio());
        }
        return leaderAllDTO;
    }


    private LeaderDTO fixSpacesCampsLeader(LeaderModel leaderModel) {
        LeaderDTO leaderDTO = new LeaderDTO();
        BeanUtils.copyProperties(leaderModel, leaderDTO);
        if(leaderDTO.getIdLider() != null) {
            leaderDTO.setIdLider(leaderModel.getIdLider());
        }
        if(leaderDTO.getIdCristiano() != null) {
            leaderDTO.setIdCristiano(leaderModel.getIdCristiano());
        }
        if(leaderDTO.getGrado() != null) {
            leaderDTO.setGrado(leaderModel.getGrado().trim());
        }
        if(leaderDTO.getLiderServidor() != null) {
            leaderDTO.setLiderServidor(leaderModel.getLiderServidor().trim());
        }
        if(leaderDTO.getAreaServicio() != null) {
            leaderDTO.setAreaServicio(leaderModel.getAreaServicio());
        }
        if(leaderDTO.getEstado() != null) {
            leaderDTO.setEstado(leaderModel.getEstado().trim());
        }
        if(leaderDTO.getLiderCelular() != null) {
            leaderDTO.setLiderCelular(leaderModel.getLiderCelular());
        }
        if(leaderDTO.getRol() != null) {
            leaderDTO.setRol(leaderModel.getRol().trim());
        }
        return leaderDTO;
    }


}
