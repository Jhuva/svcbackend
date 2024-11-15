package com.svcbackend.service.impl;

import com.svcbackend.dto.MemberStateDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.MemberStateMapper;
import com.svcbackend.model.MemberStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.MemberStateService;
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
public class MemberStateImpl implements MemberStateService {

    private final MemberStateMapper memberStateMapper;

    @Override
    public GenericResponse<Object> findAllMemberState() throws GenericException {
        log.info("Obteniendo los Estados de miembros...");
        try {
            List<MemberStateModel> listMemberState = memberStateMapper.findAllMemberState();
            List<MemberStateDTO> listMemberStateDTO = new ArrayList<>();
            if(listMemberState != null && !listMemberState.isEmpty()) {
                listMemberState.forEach(memberState -> {
                    MemberStateDTO memberStateDTO = fixSpacesCampsMemberState(memberState);
                    listMemberStateDTO.add(memberStateDTO);
                });
                log.info("Estados de miembros obtenidos");
                return new GenericResponse<>(true, listMemberStateDTO, "Estados de miembros obtenidos");
            } else {
                log.info("No se cuenta con Estados de Miembros");
                return new GenericResponse<>(false, "No hay lista de Estados de Miembros");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdMemberState(Integer idMemberState) throws GenericException {
        log.info("Obteniendo el estado de miembro {}...", idMemberState);
        try {
            MemberStateModel memberStateRes = memberStateMapper.findByIdMemberState(idMemberState);
            if(memberStateRes != null) {
                MemberStateDTO memberStateDTO = fixSpacesCampsMemberState(memberStateRes);
                log.info("Estado miembro {}", idMemberState);
                return new GenericResponse<>(true, memberStateDTO, "Estado miembro " + idMemberState + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Estado miembro {}", idMemberState);
                return new GenericResponse<>(false, "No se cuenta con el Estado miembro " + idMemberState);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerMemberState(MemberStateModel memberStateModel) {
        log.info("Registrando el Estado miembro {}... ", memberStateModel.getIdEstadoMiembro());
        try {
            MemberStateModel idMemberState = memberStateMapper.findByIdMemberState(memberStateModel.getIdEstadoMiembro());
            if(idMemberState != null) {
                log.error("Error al guardar el Estado miembro. El Estado miembro {} ya existe.", memberStateModel.getIdEstadoMiembro());
                return new GenericResponse<>(false, "Error al guardar el Estado miembro " + memberStateModel.getEstadoMiembro() + " ya existe.");
            } else {
                log.info("Se ha registrado el Estado miembro {}.", memberStateModel.getIdEstadoMiembro());
                memberStateMapper.registerMemberState(memberStateModel);
                return new GenericResponse<>(true, "Se ha registrado el Estado miembro " + memberStateModel.getEstadoMiembro());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Estado miembro " + memberStateModel.getEstadoMiembro());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateMemberState(MemberStateModel memberStateModel) {
        log.info("Actualizando el Estado miembro {}... ", memberStateModel.getIdEstadoMiembro());
        try {
            if(memberStateModel.getIdEstadoMiembro() != null) {
                MemberStateModel idMemberState = memberStateMapper.findByIdMemberState(memberStateModel.getIdEstadoMiembro());
                if(idMemberState != null) {
                    memberStateMapper.updateMemberState(memberStateModel);
                    return new GenericResponse<>(true, "Estado miembro " + memberStateModel.getEstadoMiembro() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Estado miembro {}. No existe", memberStateModel.getIdEstadoMiembro());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Estado miembro " + memberStateModel.getEstadoMiembro() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Estado miembro no debe ser nulo");
                return new GenericResponse<>(false, "Id del Estado miembro es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Estado miembro " + memberStateModel.getEstadoMiembro());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteMemberState(Integer idMemberState) {
        log.info("Eliminando Estado miembro {}...", idMemberState);
        try {
            MemberStateModel memberStateRes = memberStateMapper.findByIdMemberState(idMemberState);
            if(memberStateRes.getIdEstadoMiembro() != null) {
                memberStateMapper.deleteMemberState(idMemberState);
                log.info("Estado miembro eliminado {}", idMemberState);
                return new GenericResponse<>(true, "Estado miembro " + memberStateRes.getEstadoMiembro() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Estado miembro {}", idMemberState);
                return new GenericResponse<>(false, "No se cuenta con el Estado miembro " + memberStateRes.getEstadoMiembro());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Estado miembro " + idMemberState);
        }
    }

    private MemberStateDTO fixSpacesCampsMemberState(MemberStateModel memberStateModel) {
        MemberStateDTO memberStateDTO = new MemberStateDTO();
        BeanUtils.copyProperties(memberStateModel, memberStateDTO);
        if(memberStateDTO.getIdEstadoMiembro() != null) {
            memberStateDTO.setIdEstadoMiembro(memberStateModel.getIdEstadoMiembro());
        }
        if(memberStateDTO.getEstadoMiembro() != null) {
            memberStateDTO.setEstadoMiembro(memberStateModel.getEstadoMiembro().trim());
        }
        return memberStateDTO;
    }

}
