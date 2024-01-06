package com.unibuc.FTR.service.abstractions;

import com.unibuc.FTR.dto.PlayerDto;

import java.text.ParseException;

public interface PlayerService extends GenericService<PlayerDto>{
    public void deletePlayerFromRoster(Integer playerId);
    public PlayerDto addPlayerToRoster(Integer playerId, Integer rosterId);
}
