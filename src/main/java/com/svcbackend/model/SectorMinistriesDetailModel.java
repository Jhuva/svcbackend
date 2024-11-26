package com.svcbackend.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SectorMinistriesDetailModel {
    private String nombre;
    private String nombreColPast;
    private String nombreZona;
    private Integer cantSupervisiones;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer liderA;
    private Integer liderB;
    private Integer total;
    private List<Map<String, Object>> planSupervisiones;
    private List<Map<String, Object>> planCelulas;
    private List<Map<String, Object>> planLideres;
}
