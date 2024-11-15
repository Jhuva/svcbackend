package com.svcbackend.service.impl;

import com.svcbackend.dto.CellDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.CellMapper;
import com.svcbackend.model.CellModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CellService;
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
public class CellImpl implements CellService {

    private final CellMapper cellMapper;

    @Override
    public GenericResponse<Object> findAllCell() throws GenericException {
        log.info("Obteniendo las Celulas...");
        try {
            List<CellModel> listCell = cellMapper.findAllCell();
            List<CellDTO> listCellDTO = new ArrayList<>();
            if(listCell != null && !listCell.isEmpty()) {
                listCell.forEach(cell -> {
                    CellDTO cellDTO = fixSpacesCampsCell(cell);
                    listCellDTO.add(cellDTO);
                });
                log.info("Celulas obtenidos");
                return new GenericResponse<>(true, listCellDTO, "Celulas obtenidos");
            } else {
                log.info("No se cuenta con las celulas");
                return new GenericResponse<>(false, "No hay lista de Celulas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdCell(Integer idCell) throws GenericException {
        log.info("Obteniendo la celula {}...", idCell);
        try {
            CellModel cellRes = cellMapper.findByIdCell(idCell);
            if(cellRes != null) {
                CellDTO cellDTO = fixSpacesCampsCell(cellRes);
                log.info("Celula {}", idCell);
                return new GenericResponse<>(true, cellDTO, "Célula " + idCell + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente celula {}", idCell);
                return new GenericResponse<>(false, "No se cuenta con la célula " + idCell);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerCell(CellModel cellModel) {
        log.info("Registrando la Celula {}... ", cellModel.getIdCelula());
        try {
            CellModel idCell = cellMapper.findByIdCell(cellModel.getIdCelula());
            if(idCell != null) {
                log.error("Error al guardar la Celula. La Celula {} ya existe.", cellModel.getIdCelula());
                return new GenericResponse<>(false, "Error al guardar la Celula " + cellModel.getNumeroCelula() + " ya existe.");
            } else {
                log.info("Se ha registrado la Celula {}.", cellModel.getIdCelula());
                cellMapper.registerCell(cellModel);
                return new GenericResponse<>(true, "Se ha registrado la Célula " + cellModel.getNumeroCelula());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar la Célula " + cellModel.getNumeroCelula());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateCell(CellModel cellModel) {
        log.info("Actualizando la Celula {}... ", cellModel.getIdCelula());
        try {
            if(cellModel.getIdCelula() != null) {
                CellModel idCell = cellMapper.findByIdCell(cellModel.getIdCelula());
                if(idCell != null) {
                    cellMapper.updateCell(cellModel);
                    return new GenericResponse<>(true, "Célula " + cellModel.getNumeroCelula() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar la Celula {}. No existe", cellModel.getIdCelula());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Célula " + cellModel.getNumeroCelula() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. La Celula no debe ser nulo");
                return new GenericResponse<>(false, "Id de la Célula es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar la Célula " + cellModel.getNumeroCelula());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteCell(Integer idCell) {
        log.info("Eliminando Celula {}...", idCell);
        try {
            CellModel cellRes = cellMapper.findByIdCell(idCell);
            if(cellRes.getIdCelula() != null) {
                cellMapper.deleteCell(idCell);
                log.info("Celula eliminado {}", idCell);
                return new GenericResponse<>(true, "Célula " + cellRes.getNumeroCelula() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Celula {}", idCell);
                return new GenericResponse<>(false, "No se cuenta con la Célula " + cellRes.getNumeroCelula());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar la Célula " + idCell);
        }
    }

    private CellDTO fixSpacesCampsCell(CellModel cellModel) {
        CellDTO cellDTO = new CellDTO();
        BeanUtils.copyProperties(cellModel, cellDTO);
        if(cellDTO.getIdCelula() != null) {
            cellDTO.setIdCelula(cellModel.getIdCelula());
        }
        if(cellDTO.getIdSupervision() != null) {
            cellDTO.setIdSupervision(cellModel.getIdSupervision());
        }
        if(cellDTO.getNumeroCelula() != null) {
            cellDTO.setNumeroCelula(cellModel.getNumeroCelula().trim());
        }
        if(cellDTO.getNombreLider() != null) {
            cellDTO.setNombreLider(cellModel.getNombreLider().trim());
        }
        if(cellDTO.getDireccion() != null) {
            cellDTO.setDireccion(cellModel.getDireccion().trim());
        }
        if(cellDTO.getEstado() != null) {
            cellDTO.setEstado(cellModel.getEstado().trim());
        }
        return cellDTO;
    }

}
