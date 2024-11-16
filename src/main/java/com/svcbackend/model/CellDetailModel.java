package com.svcbackend.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CellDetailModel {
    private String nombreLider;
    private String direccion;
    private String estado;
    private String numeroCelula;
    private String nombreSupervision;
    private String nombreZona;
    private String sectorMinisterio;
    private Integer cantMiembros;
    private Integer cantAsistentes;
    private Integer total;
    private List<Map<String, Object>> miembros;
    private List<Map<String, Object>> asistentes;
}
