package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ServiceAreaModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ServiceAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/service-area")
public class ServiceAreaController {

    private final ServiceAreaService serviceAreaService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllServiceArea() throws GenericException {
        return serviceAreaService.findAllServiceArea();
    }

    @GetMapping("/{idServiceArea}")
    public GenericResponse<Object> findByIdServiceArea(@PathVariable("idServiceArea") Integer idServiceArea) throws GenericException {
        return serviceAreaService.findByIdServiceArea(idServiceArea);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerServiceArea(@RequestBody ServiceAreaModel serviceArea) throws GenericException {
        return serviceAreaService.registerServiceArea(serviceArea);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateServiceArea(@RequestBody ServiceAreaModel serviceArea) throws GenericException {
        return serviceAreaService.updateServiceArea(serviceArea);
    }

    @DeleteMapping("/delete/{idServiceArea}")
    public GenericResponse<Object> deleteServiceArea(@PathVariable("idServiceArea") Integer idServiceArea) throws GenericException {
        return serviceAreaService.deleteServiceArea(idServiceArea);
    }

}
