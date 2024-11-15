package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.SectorMinistriesModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.SectorMinistriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sector-ministries")
public class SectorMinistriesController {

    private final SectorMinistriesService sectorMinistriesService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllSectorMinistries() throws GenericException {
        return sectorMinistriesService.findAllMinistriesService();
    }

    @GetMapping("/{idSectorMinistries}")
    public GenericResponse<Object> findByIdSectorMinistries(@PathVariable("idSectorMinistries") Integer idSectorMinistries) throws GenericException {
        return sectorMinistriesService.findByIdMinistriesService(idSectorMinistries);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerSectorMinistries(@RequestBody SectorMinistriesModel sectorMinistries) throws GenericException {
        return sectorMinistriesService.registerMinistriesService(sectorMinistries);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateSectorMinistries(@RequestBody SectorMinistriesModel sectorMinistries) throws GenericException {
        return sectorMinistriesService.updateMinistriesService(sectorMinistries);
    }

    @DeleteMapping("/delete/{idSectorMinistries}")
    public GenericResponse<Object> deleteSectorMinistries(@PathVariable("idSectorMinistries") Integer idSectorMinistries) throws GenericException {
        return sectorMinistriesService.deleteMinistriesService(idSectorMinistries);
    }

}
