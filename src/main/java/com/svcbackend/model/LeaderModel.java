package com.svcbackend.model;

import lombok.Data;

@Data
public class LeaderModel {
    private Integer idLider;
    private Integer idCristiano;
    private String grado;
    private String liderServidor;
    private String areaServicio;
    private String estado;
    private String liderCelular;
    private String rol;
}
