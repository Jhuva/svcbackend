package com.svcbackend.mapper;

import com.svcbackend.model.CellModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CellMapper {
    List<CellModel> findAllCell();
    CellModel findByIdCell(@Param("idCelula") Integer idCelula);
    void registerCell(CellModel cellModel);
    void updateCell(CellModel cellModel);
    void deleteCell(@Param("idCelula") Integer idCelula);
}
