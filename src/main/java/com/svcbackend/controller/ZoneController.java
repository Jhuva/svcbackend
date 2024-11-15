package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ZoneModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/zone")
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllZone() throws GenericException {
        return zoneService.findAllZone();
    }

    @GetMapping("/{idZone}")
    public GenericResponse<Object> findByIdZone(@PathVariable("idZone") Integer idZone) throws GenericException {
        return zoneService.findByIdZone(idZone);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerZone(@RequestBody ZoneModel zone) throws GenericException {
        return zoneService.registerZone(zone);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateZone(@RequestBody ZoneModel zone) throws GenericException {
        return zoneService.updateZone(zone);
    }

    @DeleteMapping("/delete/{idZone}")
    public GenericResponse<Object> deleteZone(@PathVariable("idZone") Integer idZone) throws GenericException {
        return zoneService.deleteZone(idZone);
    }

}
