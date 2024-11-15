package com.svcbackend.dto;

import lombok.Data;

@Data
public class LeaderDTO {
    private Integer idLider;
    private Integer idCristiano;
    private String grado;
    private String liderServidor;
    private String areaServicio;
    private String estado;
    private String liderCelular;
    private String rol;
}
