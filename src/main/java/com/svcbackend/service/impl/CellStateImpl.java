package com.svcbackend.service.impl;

import com.svcbackend.dto.CellStateDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.CellStateMapper;
import com.svcbackend.model.CellStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CellStateService;
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
public class CellStateImpl implements CellStateService {

    private final CellStateMapper cellStateMapper;

    @Override
    public GenericResponse<Object> findAllCellState() throws GenericException {
        log.info("Obteniendo los Estados de células...");
        try {
            List<CellStateModel> listCellState = cellStateMapper.findAllCellState();
            List<CellStateDTO> listCellStateDTO = new ArrayList<>();
            if(listCellState != null && !listCellState.isEmpty()) {
                listCellState.forEach(cellState -> {
                    CellStateDTO cellStateDTO = fixSpacesCampsCellState(cellState);
                    listCellStateDTO.add(cellStateDTO);
                });
                log.info("Estados de Celulas obtenidos");
                return new GenericResponse<>(true, listCellStateDTO, "Estados de células obtenidos");
            } else {
                log.info("No se cuenta con Estados de Celulas");
                return new GenericResponse<>(false, "No hay Estados de Células");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdCellState(Integer idCellState) throws GenericException {
        log.info("Obteniendo Estado de Celula {}...", idCellState);
        try {
            CellStateModel cellStateRes = cellStateMapper.findByIdCellState(idCellState);
            if(cellStateRes != null) {
                CellStateDTO cellStateDTO = fixSpacesCampsCellState(cellStateRes);
                log.info("Estado de Celula {}", idCellState);
                return new GenericResponse<>(true, cellStateDTO, "Estado de Célula " + idCellState + " obtenido.");
            } else {
                log.info("No se cuenta con el siguiente Estado de celula {}", idCellState);
                return new GenericResponse<>(false, "No se cuenta con el Estado de Célula " + idCellState);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerCellState(CellStateModel cellStateModel) {
        log.info("Registrando el Estado de Celula {}... ", cellStateModel.getIdEstadoCelula());
        try {
            CellStateModel idCellState = cellStateMapper.findByIdCellState(cellStateModel.getIdEstadoCelula());
            if(idCellState != null) {
                log.error("Error al guardar el Estado de Celula. El Estado de celula {} ya existe.", cellStateModel.getIdEstadoCelula());
                return new GenericResponse<>(false, "Error al guardar el Estado de Célula " + cellStateModel.getEstadoCelula() + " ya existe.");
            } else {
                log.info("Se ha registrado el Estado de Celula {}.", cellStateModel.getIdEstadoCelula());
                cellStateMapper.registerCellState(cellStateModel);
                return new GenericResponse<>(true, "Se ha registrado el Estado de Célula " + cellStateModel.getEstadoCelula());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el Estado de Célula " + cellStateModel.getEstadoCelula());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateCellState(CellStateModel cellStateModel) {
        log.info("Actualizando el Estado de Célula {}... ", cellStateModel.getIdEstadoCelula());
        try {
            if(cellStateModel.getIdEstadoCelula() != null) {
                CellStateModel idCellState = cellStateMapper.findByIdCellState(cellStateModel.getIdEstadoCelula());
                if(idCellState != null) {
                    cellStateMapper.updateCellState(cellStateModel);
                    return new GenericResponse<>(true, "Estado de Célula " + cellStateModel.getEstadoCelula() + " actualizado.");
                } else {
                    log.info("Error al momento de actualizar el Estado de celula {}. No existe", cellStateModel.getIdEstadoCelula());
                    return new GenericResponse<>(false, "Error al momento de actualizar el Estado de Célula " + cellStateModel.getEstadoCelula() + " no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El Estado de Celula no debe ser nulo");
                return new GenericResponse<>(false, "Id del Estado de Célula es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el Estado de Célula " + cellStateModel.getEstadoCelula());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteCellState(Integer idCellState) {
        log.info("Eliminando Estado de Celula {}...", idCellState);
        try {
            CellStateModel cellStateRes = cellStateMapper.findByIdCellState(idCellState);
            if(cellStateRes.getIdEstadoCelula() != null) {
                cellStateMapper.deleteCellState(idCellState);
                log.info("Estado de Celula eliminado {}", idCellState);
                return new GenericResponse<>(true, "Estado de Célula " + cellStateRes.getEstadoCelula() + " eliminado.");
            } else {
                log.info("No se cuenta con el siguiente Estado de celula {}", idCellState);
                return new GenericResponse<>(false, "No se cuenta con el Estado de Célula " + cellStateRes.getEstadoCelula());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el Estado de Célula " + idCellState);
        }
    }

    private CellStateDTO fixSpacesCampsCellState(CellStateModel cellStateModel) {
        CellStateDTO cellStateDTO = new CellStateDTO();
        BeanUtils.copyProperties(cellStateModel, cellStateDTO);
        if(cellStateDTO.getIdEstadoCelula() != null) {
            cellStateDTO.setIdEstadoCelula(cellStateModel.getIdEstadoCelula());
        }
        if(cellStateDTO.getEstadoCelula() != null) {
            cellStateDTO.setEstadoCelula(cellStateModel.getEstadoCelula().trim());
        }
        return cellStateDTO;
    }

}
