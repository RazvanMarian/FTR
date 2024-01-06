package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.dto.TeamInformationDto;
import com.unibuc.FTR.service.abstractions.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeamDto> AddTeam(@Valid @RequestBody TeamDto team)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(team));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeamDto>> GetAllTeams()
    {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllTeams());
    }

    @GetMapping("/getByName")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<TeamDto> GetTeam(@RequestParam("name") String name)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(teamService.getTeamByName(name));
    }

    @GetMapping("/getInformations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeamInformationDto> GetInfos(@RequestParam("teamId") Integer teamId,
                                                              @RequestParam("season") String season)
    {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeamInformation(teamId, season));
    }
}
