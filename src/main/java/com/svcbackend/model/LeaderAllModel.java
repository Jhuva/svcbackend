package com.svcbackend.model;

import lombok.Data;

@Data
public class LeaderAllModel {
    private Integer idLider;
    private Integer idCristiano;
    private String nombres;
    private String apellidos;
    private String estado;
    private String areaServicio;
    private String liderServidor;
    private String grado;
    private String rol;
    private String liderCelular;
    private String sectorMinisterio;
}
