package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerTransferDto;
import com.unibuc.FTR.service.abstractions.PlayerTransfersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("playerTransfer")
public class PlayerTransferController {
    @Autowired
    PlayerTransfersService playerTransfersService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerTransferDto> Add(@Valid @RequestBody PlayerTransferDto playerTransfer)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerTransfersService.save(playerTransfer));
    }
}
