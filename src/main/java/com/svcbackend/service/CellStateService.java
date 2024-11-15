package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CellStateModel;
import com.svcbackend.response.GenericResponse;

public interface CellStateService {

    GenericResponse<Object> findAllCellState() throws GenericException;
    GenericResponse<Object> findByIdCellState(Integer idCellState) throws GenericException;
    GenericResponse<Object> registerCellState(CellStateModel cellState);
    GenericResponse<Object> updateCellState(CellStateModel cellState);
    GenericResponse<Object> deleteCellState(Integer idCellState);

}
