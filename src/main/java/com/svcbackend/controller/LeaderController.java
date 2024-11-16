package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.LeaderModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.LeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/leader")
public class LeaderController {

    private final LeaderService leaderService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllLeader() throws GenericException {
        return leaderService.findAllLeader();
    }

    @GetMapping("/table")
    public GenericResponse<Object> findAllTableLeader() throws GenericException {
        return leaderService.findAllTableLeader();
    }

    @GetMapping("/numbers")
    public GenericResponse<Object> findLeaderNumbers() throws GenericException {
        return leaderService.findLeaderNumbers();
    }

    @GetMapping("/{idLeader}")
    public GenericResponse<Object> findByIdLeader(@PathVariable("idLeader") Integer idLeader) throws GenericException {
        return leaderService.findByIdLeader(idLeader);
    }

    @GetMapping("/detail/{idLeader}")
    public GenericResponse<Object> findByIdLeaderDetail(@PathVariable("idLeader") Integer idLeader) throws GenericException {
        return leaderService.findByIdLeaderDetail(idLeader);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerLeader(@RequestBody LeaderModel leader) throws GenericException {
        return leaderService.registerLeader(leader);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateLeader(@RequestBody LeaderModel leader) throws GenericException {
        return leaderService.updateLeader(leader);
    }

    @DeleteMapping("/delete/{idLeader}")
    public GenericResponse<Object> deleteLeader(@PathVariable("idLeader") Integer idLeader) throws GenericException {
        return leaderService.deleteLeader(idLeader);
    }

}
