package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.RolModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.RolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rol")
public class RolController {

    private final RolService rolService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllRol() throws GenericException {
        return rolService.findAllRol();
    }

    @GetMapping("/{idRol}")
    public GenericResponse<Object> findByIdRol(@PathVariable("idRol") Integer idRol) throws GenericException {
        return rolService.findByIdRol(idRol);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerRol(@RequestBody RolModel rol) throws GenericException {
        return rolService.registerRol(rol);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateRol(@RequestBody RolModel rol) throws GenericException {
        return rolService.updateRol(rol);
    }

    @DeleteMapping("/delete/{idRol}")
    public GenericResponse<Object> deleteRol(@PathVariable("idRol") Integer idRol) throws GenericException {
        return rolService.deleteRol(idRol);
    }

}
