package com.svcbackend.dto;

import lombok.Data;

@Data
public class CellAllDTO {
    private Integer idCelula;
    private Integer idSupervision;
    private String numeroCelula;
    private String sectorMinisterio;
    private Integer miembros;
    private Integer asistentes;
    private Integer total;
    private String nombreLider;
    private String direccion;
    private String estado;
}
