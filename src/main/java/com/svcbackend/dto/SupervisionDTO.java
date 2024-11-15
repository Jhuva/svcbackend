package com.svcbackend.dto;

import lombok.Data;

@Data
public class SupervisionDTO {
    private Integer idSupervision;
    private Integer idSectorMinisterio;
    private String nombreSupervision;
    private String nombreSupervisor;
}
