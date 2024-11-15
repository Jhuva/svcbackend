package com.svcbackend.model;

import lombok.Data;

@Data
public class CellModel {
    private Integer idCelula;
    private Integer idSupervision;
    private String numeroCelula;
    private String nombreLider;
    private String direccion;
    private String estado;
}
