package com.unibuc.FTR.service.abstractions;

import com.unibuc.FTR.dto.PlayerStatisticsDto;

public interface PlayerStatisticsService extends GenericService<PlayerStatisticsDto> {

    PlayerStatisticsDto getPlayerStatistics(Integer playerId, String season);
}
