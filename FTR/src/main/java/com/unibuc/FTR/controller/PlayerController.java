package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.service.abstractions.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerDto> AddPlayer(@Valid @RequestBody PlayerDto playerDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(playerDto));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDto> EditPlayer(@Valid @RequestBody PlayerDto playerDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.save(playerDto));
    }

    @DeleteMapping("/deleteFromRoster")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDto> DeletePlayerFromRoster(@RequestParam Integer playerId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.deletePlayerFromRoster(playerId));
    }

    @PostMapping("/addPlayerToRoster")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDto> AddPlayerToRoster(@RequestParam Integer playerId,
                                                    @RequestParam Integer rosterId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.addPlayerToRoster(playerId, rosterId));
    }
}
