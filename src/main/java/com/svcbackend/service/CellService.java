package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CellModel;
import com.svcbackend.response.GenericResponse;

public interface CellService {
    GenericResponse<Object> findAllCell() throws GenericException;
    GenericResponse<Object> findAllTableCell() throws GenericException;
    GenericResponse<Object> findCellNumbers() throws GenericException;
    GenericResponse<Object> findByIdCell(Integer idCelula) throws GenericException;
    GenericResponse<Object> findByIdCellDetail(Integer idCelula) throws GenericException;
    GenericResponse<Object> registerCell(CellModel cell);
    GenericResponse<Object> updateCell(CellModel cell);
    GenericResponse<Object> deleteCell(Integer idCelula);
}
