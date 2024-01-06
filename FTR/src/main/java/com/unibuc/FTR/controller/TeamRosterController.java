package com.unibuc.FTR.controller;


import com.unibuc.FTR.dto.TeamRosterDto;
import com.unibuc.FTR.service.abstractions.TeamRosterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teamRoster")
public class TeamRosterController {
    @Autowired
    private TeamRosterService teamRosterService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamRosterDto> AddTeam(@Valid @RequestBody TeamRosterDto teamRosterDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamRosterService.save(teamRosterDto));
    }
}
