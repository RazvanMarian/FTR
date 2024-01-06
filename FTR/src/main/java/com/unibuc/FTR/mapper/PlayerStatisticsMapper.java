package com.unibuc.FTR.mapper;

import com.unibuc.FTR.dto.PlayerStatisticsDto;
import com.unibuc.FTR.entity.PlayerStatistics;

public class PlayerStatisticsMapper {
    public static PlayerStatisticsDto convertToDto(PlayerStatistics playerStatistics)
    {
        return PlayerStatisticsDto.builder()
                .id(playerStatistics.getId())
                .playerId(playerStatistics.getPlayer().getId())
                .goals(playerStatistics.getGoals())
                .assists(playerStatistics.getAssists())
                .season(playerStatistics.getSeason())
                .build();
    }

    public static PlayerStatistics convertToPlayerStatistics(PlayerStatisticsDto playerStatisticsDto)
    {
        return PlayerStatistics.builder()
                .id(playerStatisticsDto.getId())
                .season(playerStatisticsDto.getSeason())
                .goals(playerStatisticsDto.getGoals())
                .assists(playerStatisticsDto.getAssists())
                .build();
    }
}
