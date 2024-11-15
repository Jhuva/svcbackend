package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CivilStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CivilStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/civil-state")
public class CivilStateController {

    private final CivilStateService civilStateService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllCivilState() throws GenericException {
        return civilStateService.findAllCivilState();
    }

    @GetMapping("/{idCivilState}")
    public GenericResponse<Object> findByIdCivilState(@PathVariable("idCivilState") Integer idCivilState) throws GenericException {
        return civilStateService.findByIdCivilState(idCivilState);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerCivilState(@RequestBody CivilStateModel civilState) throws GenericException {
        return civilStateService.registerCivilState(civilState);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateCivilState(@RequestBody CivilStateModel civilState) throws GenericException {
        return civilStateService.updateCivilState(civilState);
    }

    @DeleteMapping("/delete/{idCivilState}")
    public GenericResponse<Object> deleteCivilState(@PathVariable("idCivilState") Integer idCivilState) throws GenericException {
        return civilStateService.deleteCivilState(idCivilState);
    }

}
