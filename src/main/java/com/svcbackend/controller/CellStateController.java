package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CellStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CellStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cell-state")
public class CellStateController {

    private final CellStateService cellStateService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllCellState() throws GenericException {
        return cellStateService.findAllCellState();
    }

    @GetMapping("/{idCellState}")
    public GenericResponse<Object> findByIdCellState(@PathVariable("idCellState") Integer idCellState) throws GenericException {
        return cellStateService.findByIdCellState(idCellState);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerCellState(@RequestBody CellStateModel cellState) throws GenericException {
        return cellStateService.registerCellState(cellState);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateCellState(@RequestBody CellStateModel cellState) throws GenericException {
        return cellStateService.updateCellState(cellState);
    }

    @DeleteMapping("/delete/{idCellState}")
    public GenericResponse<Object> deleteCellState(@PathVariable("idCellState") Integer idCellState) throws GenericException {
        return cellStateService.deleteCellState(idCellState);
    }

}
