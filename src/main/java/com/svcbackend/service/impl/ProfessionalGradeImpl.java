package com.svcbackend.service.impl;

import com.svcbackend.dto.ProfessionalGradeDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.ProfessionalGradeMapper;
import com.svcbackend.model.ProfessionalGradeModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ProfessionalGradeService;
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
public class ProfessionalGradeImpl implements ProfessionalGradeService {

    private final ProfessionalGradeMapper professionalGradeMapper;

    @Override
    public GenericResponse<Object> findAllProfessionalGrade() throws GenericException {
        log.info("Obteniendo los grados profesionales...");
        try {
            List<ProfessionalGradeModel> listProfessionalGrade = professionalGradeMapper.findAllProfessionalGrade();
            List<ProfessionalGradeDTO> listProfessionalGradeDTO = new ArrayList<>();
            if(listProfessionalGrade != null && !listProfessionalGrade.isEmpty()) {
                listProfessionalGrade.forEach(professionalGrade -> {
                    ProfessionalGradeDTO professionalGradeDTO = fixSpacesCampsProfessionalGrade(professionalGrade);
                    listProfessionalGradeDTO.add(professionalGradeDTO);
                });
                log.info("Grados profesionales obtenidos");
                return new GenericResponse<>(true, listProfessionalGradeDTO, "Grados profesionales obtenidos");
            } else {
                log.info("No se cuenta con Grados profesionales");
                return new GenericResponse<>(false, "No hay lista de Grados profesionales");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdProfessionalGrade(Integer idProfessionalGrade) throws GenericException {
        log.info("Obteniendo el Grado profesional {}...", idProfessionalGrade);
        try {
            ProfessionalGradeModel professionalGradeRes = professionalGradeMapper.findByIdProfessionalGrade(idProfessionalGrade);
            if(professionalGradeRes != null) {
                ProfessionalGradeDTO professionalGradeDTO = fixSpacesCampsProfessionalGrade(professionalGradeRes);
                log.info("Grado profesional {}", idProfessionalGrade);
                return new GenericResponse<>(true, professionalGradeDTO, "Grado profesional " + idProfessionalGrade + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Grado profesional {}", idProfessionalGrade);
                return new GenericResponse<>(false, "No se cuenta con el Grado profesional " + idProfessionalGrade);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerProfessionalGrade(ProfessionalGradeModel professionalGradeModel) {
        log.info("Registrando el Grado profesional {}... ", professionalGradeModel.getIdGradoProfesional());
        try {
            ProfessionalGradeModel idProfessionalGrade = professionalGradeMapper.findByIdProfessionalGrade(professionalGradeModel.getIdGradoProfesional());
            if(idProfessionalGrade != null) {
                log.error("Error al guardar el Grado profesional. El Grado profesional {} ya existe.", professionalGradeModel.getIdGradoProfesional());
                return new GenericResponse<>(false, "Error al guardar el Grado profesional " + professionalGradeModel.getGradoProfesional() + " ya existe.");
            } else {
                log.info("Se ha registrado el Grado profesional {}.", professionalGradeModel.getIdGradoProfesional());
                professionalGradeMapper.registerProfessionalGrade(professionalGradeModel);
                return new GenericResponse<>(true, "Se ha registrado el Grado profesional " + professionalGradeModel.getGradoProfesional());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Grado profesional " + professionalGradeModel.getGradoProfesional());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateProfessionalGrade(ProfessionalGradeModel professionalGradeModel) {
        log.info("Actualizando el Grado profesional {}... ", professionalGradeModel.getIdGradoProfesional());
        try {
            if(professionalGradeModel.getIdGradoProfesional() != null) {
                ProfessionalGradeModel idProfessionalGrade = professionalGradeMapper.findByIdProfessionalGrade(professionalGradeModel.getIdGradoProfesional());
                if(idProfessionalGrade != null) {
                    professionalGradeMapper.updateProfessionalGrade(professionalGradeModel);
                    return new GenericResponse<>(true, "Grado profesional " + professionalGradeModel.getGradoProfesional() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Grado profesional {}. No existe", professionalGradeModel.getIdGradoProfesional());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Grado profesional " + professionalGradeModel.getGradoProfesional() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Grado profesional no debe ser nulo");
                return new GenericResponse<>(false, "Id del Grado profesional es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Grado profesional " + professionalGradeModel.getGradoProfesional());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteProfessionalGrade(Integer idProfessionalGrade) {
        log.info("Eliminando Grado profesional {}...", idProfessionalGrade);
        try {
            ProfessionalGradeModel professionalGradeRes = professionalGradeMapper.findByIdProfessionalGrade(idProfessionalGrade);
            if(professionalGradeRes.getIdGradoProfesional() != null) {
                professionalGradeMapper.deleteProfessionalGrade(idProfessionalGrade);
                log.info("Grado profesional eliminado {}", idProfessionalGrade);
                return new GenericResponse<>(true, "Grado profesional " + professionalGradeRes.getGradoProfesional() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Grado profesional {}", idProfessionalGrade);
                return new GenericResponse<>(false, "No se cuenta con el Grado profesional " + professionalGradeRes.getGradoProfesional());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Grado profesional " + idProfessionalGrade);
        }
    }

    private ProfessionalGradeDTO fixSpacesCampsProfessionalGrade(ProfessionalGradeModel professionalGradeModel) {
        ProfessionalGradeDTO professionalGradeDTO = new ProfessionalGradeDTO();
        BeanUtils.copyProperties(professionalGradeModel, professionalGradeDTO);
        if(professionalGradeDTO.getIdGradoProfesional() != null) {
            professionalGradeDTO.setIdGradoProfesional(professionalGradeModel.getIdGradoProfesional());
        }
        if(professionalGradeDTO.getGradoProfesional() != null) {
            professionalGradeDTO.setGradoProfesional(professionalGradeModel.getGradoProfesional().trim());
        }
        return professionalGradeDTO;
    }

}
