package com.svcbackend.controller;


import com.svcbackend.exception.GenericException;
import com.svcbackend.model.LeaderStateModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.LeaderStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/leader-state")
public class LeaderStateController {

    private final LeaderStateService leaderStateService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllLeaderState() throws GenericException {
        return leaderStateService.findAllLeaderState();
    }

    @GetMapping("/{idLeaderState}")
    public GenericResponse<Object> findByIdLeaderState(@PathVariable("idLeaderState") Integer idLeaderState) throws GenericException {
        return leaderStateService.findByIdLeaderState(idLeaderState);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerLeaderState(@RequestBody LeaderStateModel leaderState) throws GenericException {
        return leaderStateService.registerLeaderState(leaderState);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateLeaderState(@RequestBody LeaderStateModel leaderState) throws GenericException {
        return leaderStateService.updateLeaderState(leaderState);
    }

    @DeleteMapping("/delete/{idLeaderState}")
    public GenericResponse<Object> deleteLeaderState(@PathVariable("idLeaderState") Integer idLeaderState) throws GenericException {
        return leaderStateService.deleteLeaderState(idLeaderState);
    }

}
