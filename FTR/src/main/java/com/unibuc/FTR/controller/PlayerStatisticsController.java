package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.dto.PlayerStatisticsDto;
import com.unibuc.FTR.service.abstractions.PlayerStatisticsService;
import com.unibuc.FTR.validator.SeasonFormat;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("playerStatistics")
public class PlayerStatisticsController {

    @Autowired
    private PlayerStatisticsService playerStatisticsService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerStatisticsDto> AddStatistics(@Valid @RequestBody PlayerStatisticsDto statisticsDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerStatisticsService.save(statisticsDto));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerStatisticsDto> EditPlayerStatistics(@Valid @RequestBody PlayerStatisticsDto statisticsDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(playerStatisticsService.save(statisticsDto));
    }

    @GetMapping("/playerStatisticsBySeason")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerStatisticsDto> EditPlayerStatistics(@RequestParam Integer playerId,
                                                                    @RequestParam String season)
    {
        return ResponseEntity.status(HttpStatus.OK).body(playerStatisticsService.getPlayerStatistics(playerId, season));
    }
}
