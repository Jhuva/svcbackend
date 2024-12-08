package com.svcbackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChristianDTO {
    private Integer idCristiano;
    private Integer idCelula;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String email;
    private Date fecNacimiento;
    private String sexo;
    private String estadoCivil;
    private String direccion;
    private String gradoProfesional;
    private String estado;
    private String rol;
    private String procedencia;
    private String ocupacion;
    private String telefono;
    private String membresia;
}
