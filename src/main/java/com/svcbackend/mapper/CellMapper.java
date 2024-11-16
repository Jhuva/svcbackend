package com.svcbackend.mapper;

import com.svcbackend.model.CellAllModel;
import com.svcbackend.model.CellDetailModel;
import com.svcbackend.model.CellModel;
import com.svcbackend.model.CellNumModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CellMapper {
    List<CellModel> findAllCell();
    List<CellAllModel> findAllTableCell();
    CellNumModel findCellNumbers();
    CellModel findByIdCell(@Param("idCelula") Integer idCelula);
    CellDetailModel findByIdCellDetail(@Param("idCelula") Integer idCelula);
    void registerCell(CellModel cellModel);
    void updateCell(CellModel cellModel);
    void deleteCell(@Param("idCelula") Integer idCelula);
}
