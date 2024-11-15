package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CellModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cell")
public class CellController {

    private final CellService cellService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllCell() throws GenericException {
        return cellService.findAllCell();
    }

    @GetMapping("/{idCell}")
    public GenericResponse<Object> findByIdCell(@PathVariable("idCell") Integer idCell) throws GenericException {
        return cellService.findByIdCell(idCell);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerCell(@RequestBody CellModel cell) throws GenericException {
        return cellService.registerCell(cell);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateCell(@RequestBody CellModel cell) throws GenericException {
        return cellService.updateCell(cell);
    }

    @DeleteMapping("/delete/{idCell}")
    public GenericResponse<Object> deleteCell(@PathVariable("idCell") Integer idCell) throws GenericException {
        return cellService.deleteCell(idCell);
    }


}
