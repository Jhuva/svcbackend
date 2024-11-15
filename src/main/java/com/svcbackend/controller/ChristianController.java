package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ChristianModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ChristianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/christian")
public class ChristianController {

    private final ChristianService christianService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllChristian() throws GenericException {
        return christianService.findAllChristian();
    }

    @GetMapping("/{idChristian}")
    public GenericResponse<Object> findByIdChristian(@PathVariable("idChristian") Integer idChristian) throws GenericException {
        return christianService.findByIdChristian(idChristian);
    }

    @GetMapping("/detail/{idChristian}")
    public GenericResponse<Object> findByIdChristianDetail(@PathVariable("idChristian") Integer idChristian) throws GenericException {
        return christianService.findByIdChristianDetail(idChristian);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerChristian(@RequestBody ChristianModel christian) throws GenericException {
        return christianService.registerChristian(christian);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateChristian(@RequestBody ChristianModel christian) throws GenericException {
        return christianService.updateChristian(christian);
    }

    @DeleteMapping("/delete/{idChristian}")
    public GenericResponse<Object> deleteChristian(@PathVariable("idChristian") Integer idChristian) throws GenericException {
        return christianService.deleteChristian(idChristian);
    }

}
