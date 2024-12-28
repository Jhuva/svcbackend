package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/numbers")
    public GenericResponse<Object> findDashNumbers() throws GenericException {
        return dashboardService.findDashNumbers();
    }

    @GetMapping("/numbers/donuts")
    public GenericResponse<Object> findDashNumbersDonuts() throws GenericException {
        return dashboardService.findDashDonuts();
    }

    @GetMapping("/list/zones")
    public GenericResponse<Object> findDashListZones() throws GenericException {
        return dashboardService.findDashListZones();
    }

    @GetMapping("/numbers/{idSectorMinisterio}")
    public GenericResponse<Object> findDashDetailZones(@PathVariable("idSectorMinisterio") Integer idSectorMinisterio) throws GenericException {
        return dashboardService.findDashDetailZones(idSectorMinisterio);
    }

}
