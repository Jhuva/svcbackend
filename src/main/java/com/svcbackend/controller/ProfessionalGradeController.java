package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.ProfessionalGradeModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.ProfessionalGradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/professional-grade")
public class ProfessionalGradeController {

    private final ProfessionalGradeService professionalGradeService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllProfessionalGrade() throws GenericException {
        return professionalGradeService.findAllProfessionalGrade();
    }

    @GetMapping("/{idProfessionalGrade}")
    public GenericResponse<Object> findByIdProfessionalGrade(@PathVariable("idProfessionalGrade") Integer idProfessionalGrade) throws GenericException {
        return professionalGradeService.findByIdProfessionalGrade(idProfessionalGrade);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerProfessionalGrade(@RequestBody ProfessionalGradeModel professionalGrade) throws GenericException {
        return professionalGradeService.registerProfessionalGrade(professionalGrade);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateProfessionalGrade(@RequestBody ProfessionalGradeModel professionalGrade) throws GenericException {
        return professionalGradeService.updateProfessionalGrade(professionalGrade);
    }

    @DeleteMapping("/delete/{idProfessionalGrade}")
    public GenericResponse<Object> deleteProfessionalGrade(@PathVariable("idProfessionalGrade") Integer idProfessionalGrade) throws GenericException {
        return professionalGradeService.deleteProfessionalGrade(idProfessionalGrade);
    }

}
