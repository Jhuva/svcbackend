package com.svcbackend.dto;

import lombok.Data;

@Data
public class CellDTO {
    private Integer idCelula;
    private Integer idSupervision;
    private String numeroCelula;
    private String nombreLider;
    private String direccion;
    private String estado;
}
