package com.unibuc.FTR.service.abstractions;

import com.unibuc.FTR.dto.PlayerDto;

public interface PlayerService extends GenericService<PlayerDto>{
    PlayerDto deletePlayerFromRoster(Integer playerId);
    PlayerDto addPlayerToRoster(Integer playerId, Integer rosterId);
}
