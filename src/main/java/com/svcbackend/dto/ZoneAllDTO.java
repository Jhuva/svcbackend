package com.svcbackend.dto;

import lombok.Data;

@Data
public class ZoneAllDTO {
    private Integer idZona;
    private String nombre;
    private String nombrePsZonal;
    private Integer cantSectores;
    private Integer cantSupervisiones;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
