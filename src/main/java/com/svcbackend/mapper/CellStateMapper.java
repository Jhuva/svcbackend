package com.svcbackend.mapper;

import com.svcbackend.model.CellStateModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CellStateMapper {

    List<CellStateModel> findAllCellState();
    CellStateModel findByIdCellState(@Param("idEstadoCelula") Integer idEstado_celula);
    void registerCellState(CellStateModel cellStateModel);
    void updateCellState(CellStateModel cellStateModel);
    void deleteCellState(@Param("idEstadoCelula") Integer idEstado_celula);

}
