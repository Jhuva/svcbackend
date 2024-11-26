package com.svcbackend.dto;

import lombok.Data;

@Data
public class SupervisionAllDTO {
    private String supervision;
    private String supervisor;
    private String sectMinisterio;
    private String nombreZona;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
