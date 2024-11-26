package com.svcbackend.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SupervisionDetailModel {
    private String supervision;
    private String supervisor;
    private String sectMinisterio;
    private String nombreZona;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer liderA;
    private Integer liderB;
    private Integer total;
    private List<Map<String, Object>> planCelulas;
    private List<Map<String, Object>> planLideres;
}
