package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.MemberStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.MemberStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member-state")
public class MemberStateController {

    private final MemberStateService memberStateService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllMemberState() throws GenericException {
        return memberStateService.findAllMemberState();
    }

    @GetMapping("/{idMemberState}")
    public GenericResponse<Object> findByIdMemberState(@PathVariable("idMemberState") Integer idMemberState) throws GenericException {
        return memberStateService.findByIdMemberState(idMemberState);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerMemberState(@RequestBody MemberStateModel memberState) throws GenericException {
        return memberStateService.registerMemberState(memberState);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateMemberState(@RequestBody MemberStateModel memberState) throws GenericException {
        return memberStateService.updateMemberState(memberState);
    }

    @DeleteMapping("/delete/{idMemberState}")
    public GenericResponse<Object> deleteMemberState(@PathVariable("idMemberState") Integer idMemberState) throws GenericException {
        return memberStateService.deleteMemberState(idMemberState);
    }

}
