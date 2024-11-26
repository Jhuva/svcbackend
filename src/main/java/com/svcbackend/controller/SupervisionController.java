package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.SupervisionModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.SupervisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/supervision")
public class SupervisionController {

    private final SupervisionService supervisionService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllSupervision() throws GenericException {
        return supervisionService.findAllSupervisionService();
    }

    @GetMapping("/table")
    public GenericResponse<Object> findAllTableSupervision() throws GenericException {
        return supervisionService.findAllTableSupervision();
    }

    @GetMapping("/numbers")
    public GenericResponse<Object> findSupervisionNumbers() throws GenericException {
        return supervisionService.findSupervisionNumbers();
    }

    @GetMapping("/{idSupervision}")
    public GenericResponse<Object> findByIdSupervision(@PathVariable("idSupervision") Integer idSupervision) throws GenericException {
        return supervisionService.findByIdSupervisionService(idSupervision);
    }

    @GetMapping("/detail/{idSupervision}")
    public GenericResponse<Object> findByIdCellDetail(@PathVariable("idSupervision") Integer idSupervision) throws GenericException {
        return supervisionService.findByIdSupervisionDetail(idSupervision);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerSupervision(@RequestBody SupervisionModel supervision) throws GenericException {
        return supervisionService.registerSupervisionService(supervision);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateSupervision(@RequestBody SupervisionModel supervision) throws GenericException {
        return supervisionService.updateSupervisionService(supervision);
    }

    @DeleteMapping("/delete/{idSupervision}")
    public GenericResponse<Object> deleteSupervision(@PathVariable("idSupervision") Integer idSupervision) throws GenericException {
        return supervisionService.deleteSupervisionService(idSupervision);
    }

}
