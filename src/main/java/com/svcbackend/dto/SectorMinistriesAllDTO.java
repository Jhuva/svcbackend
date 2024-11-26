package com.svcbackend.dto;

import lombok.Data;

@Data
public class SectorMinistriesAllDTO {
    private String nombre;
    private String nombreColPast;
    private String nombreZona;
    private Integer cantSupervisiones;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
