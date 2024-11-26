package com.svcbackend.model;

import lombok.Data;

@Data
public class ZoneAllModel {
    private String nombre;
    private String nombrePsZonal;
    private Integer cantSectores;
    private Integer cantSupervisiones;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
