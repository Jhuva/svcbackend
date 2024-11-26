package com.svcbackend.model;

import lombok.Data;

@Data
public class CommentTableModel {
    private Integer idCristiano;
    private String nombres;
    private String apellidos;
    private String estado;
    private String areaServicio;
    private String grado;
    private String rol;
    private String sectorMinisterio;
    private String zona;
}
