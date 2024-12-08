package com.svcbackend.dto;

import lombok.Data;

@Data
public class SupervisionAllDTO {
    private Integer idSectorMinisterio;
    private Integer idSupervision;
    private String nombreSupervision;
    private String nombreSupervisor;
    private String sectMinisterio;
    private String nombreZona;
    private Integer cantCelulas;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
}
