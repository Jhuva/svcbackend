package com.svcbackend.service.impl;

import com.svcbackend.dto.ChristianDTO;
import com.svcbackend.dto.ChristianDetailDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.ChristianMapper;
import com.svcbackend.model.ChristianDetailModel;
import com.svcbackend.model.ChristianModel;
import com.svcbackend.model.ChristianNumModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ChristianService;
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
public class ChristianImpl implements ChristianService {

    private final ChristianMapper christianMapper;

    @Override
    public GenericResponse<Object> findAllChristian() throws GenericException {
        log.info("Obteniendo la lista de cristianos...");
        try {
            List<ChristianModel> listChristian = christianMapper.findAllChristian();
            List<ChristianDTO> listChristianDTO = new ArrayList<>();
            if(listChristian != null && !listChristian.isEmpty()) {
                listChristian.forEach(christian -> {
                    ChristianDTO christianDTO = fixSpacesCampsChristian(christian);
                    listChristianDTO.add(christianDTO);
                });
                log.info("Lista de Cristianos obtenidos");
                return new GenericResponse<>(true, listChristianDTO, "Lista de Cristianos obtenidos");
            } else {
                log.info("No se cuenta con Cristianos");
                return new GenericResponse<>(false, "No hay lista de Cristianos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findChristianNumbers() throws GenericException {
        log.info("Obteniendo los numeros de cristianos");
        try {
            ChristianNumModel christianRes = christianMapper.findChristianNumbers();
            if(christianRes != null) {
                log.info("Numeros de Cristianos obtenidos");
                return new GenericResponse<>(true, christianRes, "Números cristianos obtenidos");
            } else {
                log.info("No se cuenta con Números de Cristianos");
                return new GenericResponse<>(false, "No hay números de Cristianos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdChristian(Integer idChristian) throws GenericException {
        log.info("Obteniendo al cristiano {}...", idChristian);
        try {
            ChristianModel christianRes = christianMapper.findByIdChristian(idChristian);
            if(christianRes != null) {
                ChristianDTO christianDTO = fixSpacesCampsChristian(christianRes);
                log.info("Cristiano {}", idChristian);
                return new GenericResponse<>(true, christianDTO, "Cristiano " + idChristian + " obtenido.");
            } else {
                log.info("No se cuenta con el cristiano {}", idChristian);
                return new GenericResponse<>(false, "No se cuenta con el cristiano " + idChristian);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdChristianDetail(Integer idChristian) throws GenericException {
        log.info("Obteniendo el detalle del crisitiano {}...", idChristian);
        try {
            ChristianDetailModel christianRes = christianMapper.findByIdChristianDetail(idChristian);
            if(christianRes != null) {
                log.info("Detalle de cristiano {}", idChristian);
                return new GenericResponse<>( true, christianRes, "Cristiano " + idChristian + " obtenido.");
            } else {
                return new GenericResponse<>(false, "Crisitiano no existe " + idChristian);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerChristian(ChristianModel christianModel) {
        log.info("Registrando a un nuevo cristiano {}... ", christianModel.getIdCristiano());
        try {
            ChristianModel idChristian = christianMapper.findByIdChristian(christianModel.getIdCristiano());
            if(idChristian != null) {
                log.error("Error al guardar al Cristiano. El Cristiano {} ya existe.", christianModel.getIdCristiano());
                return new GenericResponse<>(false, "Error al guardar al Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos() + " ya existe.");
            } else {
                log.info("Se ha registrado al Cristiano {}.", christianModel.getIdCristiano());
                christianMapper.registerChristian(christianModel);
                return new GenericResponse<>(true, "Se ha registrado al Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar al Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateChristian(ChristianModel christianModel) {
        log.info("Actualizando al Cristiano {}... ", christianModel.getIdCristiano());
        try {
            if(christianModel.getIdCristiano() != null) {
                ChristianModel idChristian = christianMapper.findByIdChristian(christianModel.getIdCristiano());
                if(idChristian != null) {
                    christianMapper.updateChristian(christianModel);
                    return new GenericResponse<>(true, "Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar al Cristiano {}. No existe", christianModel.getIdCristiano());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Cristiano no debe ser nulo");
                return new GenericResponse<>(false, "Id del Cristiano es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar al Cristiano " + christianModel.getNombres() + " " + christianModel.getApellidos());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteChristian(Integer idChristian) {
        log.info("Eliminando Cristiano {}...", idChristian);
        try {
            ChristianModel christianRes = christianMapper.findByIdChristian(idChristian);
            if(christianRes.getIdCristiano() != null) {
                christianMapper.deleteChristian(idChristian);
                log.info("Cristiano eliminado {}", idChristian);
                return new GenericResponse<>(true, "Cristiano " + christianRes.getNombres() + " " + christianRes.getApellidos() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Cristiano {}", idChristian);
                return new GenericResponse<>(false, "No se cuenta con el Cristiano " + christianRes.getNombres() + " " + christianRes.getApellidos());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar al Cristiano " + idChristian);
        }
    }

    private ChristianDTO fixSpacesCampsChristian(ChristianModel christianModel) {
        ChristianDTO christianDTO = new ChristianDTO();
        BeanUtils.copyProperties(christianModel, christianDTO);
        if(christianDTO.getIdCristiano() != null) {
            christianDTO.setIdCristiano(christianModel.getIdCristiano());
        }
        if(christianDTO.getIdCelula() != null) {
            christianDTO.setIdCelula(christianModel.getIdCelula());
        }
        if(christianDTO.getNombres() != null) {
            christianDTO.setNombres(christianModel.getNombres().trim());
        }
        if(christianDTO.getApellidos() != null) {
            christianDTO.setApellidos(christianModel.getApellidos().trim());
        }
        if(christianDTO.getEdad() != null) {
            christianDTO.setEdad(christianModel.getEdad());
        }
        if(christianDTO.getEmail() != null) {
            christianDTO.setEmail(christianModel.getEmail().trim());
        }
        if(christianDTO.getFecNacimiento() != null) {
            christianDTO.setFecNacimiento(christianModel.getFecNacimiento());
        }
        if(christianDTO.getSexo() != null) {
            christianDTO.setSexo(christianModel.getSexo().trim());
        }
        if(christianDTO.getEstadoCivil() != null) {
            christianDTO.setEstadoCivil(christianModel.getEstadoCivil().trim());
        }
        if(christianDTO.getGradoProfesional() != null) {
            christianDTO.setGradoProfesional(christianModel.getGradoProfesional().trim());
        }
        if(christianDTO.getEstado() != null) {
            christianDTO.setEstado(christianModel.getEstado().trim());
        }
        if(christianDTO.getRol() != null) {
            christianDTO.setRol(christianModel.getRol().trim());
        }
        if(christianDTO.getProcedencia() != null) {
            christianDTO.setProcedencia(christianModel.getProcedencia().trim());
        }
        if(christianDTO.getOcupacion() != null) {
            christianDTO.setOcupacion(christianModel.getOcupacion().trim());
        }
        if(christianDTO.getTelefono() != null) {
            christianDTO.setTelefono(christianModel.getTelefono().trim());
        }
        return christianDTO;
    }

}
