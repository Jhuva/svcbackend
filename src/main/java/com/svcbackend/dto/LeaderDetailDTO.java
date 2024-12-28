package com.svcbackend.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LeaderDetailDTO {
    private String nombres;
    private String apellidos;
    private Integer edad;
    private Date fecNacimiento;
    private String direccion;
    private String sexo;
    private String estadoCivil;
    private String rol;
    private String procedencia;
    private String ocupacion;
    private String gradoProfesional;
    private String liderCelular;
    private String liderServidor;
    private String areaServicio;
    private String estado;
    private String grado;
    private String numeroCelula;
    private String nombreLider;
    private String nombreSupervision;
    private String nombreSupervisor;
    private String nombreMinisterio;
    private String nombreColPast;
    private String nombreZona;
    private String nombrePsZonal;
    private Integer cantidadCelulas;
    private List<String> comentarios;
}
