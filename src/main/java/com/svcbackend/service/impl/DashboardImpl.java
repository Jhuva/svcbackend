package com.svcbackend.service.impl;

import com.svcbackend.dto.*;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.DashboardMapper;
import com.svcbackend.model.*;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DashboardImpl implements DashboardService {

    private final DashboardMapper dashboardMapper;

    @Override
    public GenericResponse<Object> findDashNumbers() throws GenericException {
        log.info("Obteniendo los numeros de dashboard");
        try {
            DashNumbersModel dashNumberRes = dashboardMapper.findDashNumbers();
            if(dashNumberRes != null) {
                log.info("Numeros de Dashboard obtenidos");
                return new GenericResponse<>(true, dashNumberRes, "Números dashboard obtenidos");
            } else {
                log.info("No se cuenta con Números de Dashboard");
                return new GenericResponse<>(false, "No hay números de dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findDashDonuts() throws GenericException {
        log.info("Obteniendo los numeros de dashboard donuts");
        try {
            DashNumbersDonutsModel dashNumberRes = dashboardMapper.findDashDonuts();
            if(dashNumberRes != null) {
                log.info("Numeros de Dashboard donuts obtenidos");
                return new GenericResponse<>(true, dashNumberRes, "Números dashboard donuts obtenidos");
            } else {
                log.info("No se cuenta con Números de Dashboard donuts");
                return new GenericResponse<>(false, "No hay números de dashboard donuts");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findDashListZones() throws GenericException {
        log.info("Obteniendo la lista de zonas de dashboard");
        try {
            List<DashListZonesModel> listZones = dashboardMapper.findDashListZones();
            List<DashListZonesDTO> dashListZonesDTO= new ArrayList<>();
            if(listZones != null && !listZones.isEmpty()) {
                listZones.forEach(zones -> {
                    DashListZonesDTO listZonesDTO = fixSpacesCampsListZones(zones);
                    dashListZonesDTO.add(listZonesDTO);
                });
                log.info("Lista de zonas de dashboard obtenidos");
                return new GenericResponse<>(true, dashListZonesDTO, "Lista de zonas de dashboard obtenidos");
            } else {
                log.info("No se cuenta con Lista de zonas de dashboard");
                return new GenericResponse<>(false, "No hay lista de zonas de dashboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findDashDetailZones(Integer idSectorMinisterio) throws GenericException {
        log.info("Obteniendo dashboard Detalle por zona {}...", idSectorMinisterio);
        try {
            DashDetailZonesModel dashDetailZonesRes = dashboardMapper.findDashDetailZones(idSectorMinisterio);
            if(dashDetailZonesRes != null) {
                log.info("Numeros de dashboard Detalle por zona obtenidos");
                return new GenericResponse<>(true, dashDetailZonesRes, "Números dashboard Detalle por zona obtenidos");
            } else {
                log.info("No se cuenta con Números de dashboard Detalle por zona");
                return new GenericResponse<>(false, "No hay números de dashboard Detalle por zona");
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    private DashListZonesDTO fixSpacesCampsListZones(DashListZonesModel listZonesModel) {
        DashListZonesDTO listZonesDTO = new DashListZonesDTO();
        BeanUtils.copyProperties(listZonesModel, listZonesDTO);
        if(listZonesDTO.getIdSectorMinisterio() != null) {
            listZonesDTO.setIdSectorMinisterio(listZonesModel.getIdSectorMinisterio());
        }
        if(listZonesDTO.getNombre() != null) {
            listZonesDTO.setNombre(listZonesModel.getNombre());
        }
        return listZonesDTO;
    }

}
