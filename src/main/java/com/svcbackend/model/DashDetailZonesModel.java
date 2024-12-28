package com.svcbackend.model;

import lombok.Data;

@Data
public class DashDetailZonesModel {
    private String nombreColPast;
    private String nombre;
    private Integer totMiembros;
    private Integer totalLideres;
    private Integer totalCelulas;
    private Integer lideresA;
    private Integer lideresB;
    private Integer miembros;
    private Integer asistentes;
    private Integer lideresActivos;
    private Integer lideresNoActivos;
    private Integer celulasActivas;
    private Integer celulasNoActivas;
}
