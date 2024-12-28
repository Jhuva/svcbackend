package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.response.GenericResponse;

public interface DashboardService {
    GenericResponse<Object> findDashNumbers() throws GenericException;
    GenericResponse<Object> findDashDonuts() throws GenericException;
    GenericResponse<Object> findDashListZones() throws GenericException;
    GenericResponse<Object> findDashDetailZones(Integer idSectorMinisterio) throws GenericException;
}
