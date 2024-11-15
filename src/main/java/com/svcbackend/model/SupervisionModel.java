package com.svcbackend.model;

import lombok.Data;

@Data
public class SupervisionModel {
    private Integer idSupervision;
    private Integer idSectorMinisterio;
    private String nombreSupervision;
    private String nombreSupervisor;
}
