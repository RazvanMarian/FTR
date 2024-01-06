package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.TeamFixtureDto;
import com.unibuc.FTR.service.abstractions.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fixtures")
public class FixtureController {
    @Autowired
    private FixtureService fixtureService;

    @GetMapping("/teamFixtures")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeamFixtureDto>> GetTeamFixtures(@RequestParam("teamName") String teamName)
    {
        return ResponseEntity.status(HttpStatus.OK).body(fixtureService.getTeamFixtures(teamName));
    }
}
