package com.svcbackend.model;

import lombok.Data;

@Data
public class SectorMinistriesAllModel {
    private String nombre;
    private String nombreColPast;
    private String nombreZona;
    private Integer cantSupervisiones;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
