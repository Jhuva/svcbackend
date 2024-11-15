package com.svcbackend.service.impl;

import com.svcbackend.dto.RolDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.RolMapper;
import com.svcbackend.model.RolModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.RolService;
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
public class RolImpl implements RolService {

    private final RolMapper rolMapper;

    @Override
    public GenericResponse<Object> findAllRol() throws GenericException {
        log.info("Obteniendo los Roles...");
        try {
            List<RolModel> listRol = rolMapper.findAllRol();
            List<RolDTO> listRolDTO = new ArrayList<>();
            if(listRol != null && !listRol.isEmpty()) {
                listRol.forEach(rol -> {
                    RolDTO rolDTO = fixSpacesCampsRol(rol);
                    listRolDTO.add(rolDTO);
                });
                log.info("Roles obtenidos");
                return new GenericResponse<>(true, listRolDTO, "Roles obtenidos");
            } else {
                log.info("No se cuenta con Roles");
                return new GenericResponse<>(false, "No hay lista de Roles");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdRol(Integer idRol) throws GenericException {
        log.info("Obteniendo el rol {}...", idRol);
        try {
            RolModel rolRes = rolMapper.findByIdRol(idRol);
            if(rolRes != null) {
                RolDTO rolDTO = fixSpacesCampsRol(rolRes);
                log.info("Rol {}", idRol);
                return new GenericResponse<>(true, rolDTO, "rol " + idRol + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente rol {}", idRol);
                return new GenericResponse<>(false, "No se cuenta con el rol " + idRol);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerRol(RolModel rolModel) {
        log.info("Registrando el Rol {}... ", rolModel.getIdRol());
        try {
            RolModel idRol = rolMapper.findByIdRol(rolModel.getIdRol());
            if(idRol != null) {
                log.error("Error al guardar el Rol. El Rol {} ya existe.", rolModel.getIdRol());
                return new GenericResponse<>(false, "Error al guardar el Rol " + rolModel.getRol() + " ya existe.");
            } else {
                log.info("Se ha registrado el Rol {}.", rolModel.getIdRol());
                rolMapper.registerRol(rolModel);
                return new GenericResponse<>(true, "Se ha registrado el Rol " + rolModel.getRol());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Rol " + rolModel.getRol());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateRol(RolModel rolModel) {
        log.info("Actualizando el Rol {}... ", rolModel.getIdRol());
        try {
            if(rolModel.getIdRol() != null) {
                RolModel idRol = rolMapper.findByIdRol(rolModel.getIdRol());
                if(idRol != null) {
                    rolMapper.updateRol(rolModel);
                    return new GenericResponse<>(true, "Rol " + rolModel.getRol() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Rol {}. No existe", rolModel.getIdRol());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Rol " + rolModel.getRol() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Rol no debe ser nulo");
                return new GenericResponse<>(false, "Id del Rol es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Rol " + rolModel.getRol());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteRol(Integer idRol) {
        log.info("Eliminando Rol {}...", idRol);
        try {
            RolModel rolRes = rolMapper.findByIdRol(idRol);
            if(rolRes.getIdRol() != null) {
                rolMapper.deleteRol(idRol);
                log.info("Rol eliminado {}", idRol);
                return new GenericResponse<>(true, "Rol " + rolRes.getRol() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Rol {}", idRol);
                return new GenericResponse<>(false, "No se cuenta con el Rol " + rolRes.getRol());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Rol " + idRol);
        }
    }

    private RolDTO fixSpacesCampsRol(RolModel rolModel) {
        RolDTO rolDTO = new RolDTO();
        BeanUtils.copyProperties(rolModel, rolDTO);
        if(rolDTO.getIdRol() != null) {
            rolDTO.setIdRol(rolModel.getIdRol());
        }
        if(rolDTO.getRol() != null) {
            rolDTO.setRol(rolModel.getRol().trim());
        }
        return rolDTO;
    }

}
