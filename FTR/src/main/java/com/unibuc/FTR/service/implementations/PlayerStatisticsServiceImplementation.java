package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.PlayerStatisticsDto;
import com.unibuc.FTR.entity.PlayerStatistics;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.PlayerStatisticsMapper;
import com.unibuc.FTR.mapper.TeamRosterMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.PlayerStatisticsRepository;
import com.unibuc.FTR.service.abstractions.PlayerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsServiceImplementation implements PlayerStatisticsService {

    @Autowired
    PlayerStatisticsRepository playerStatisticsRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public PlayerStatisticsDto save(PlayerStatisticsDto dto) {

        PlayerStatistics playerStatistics;
        if(dto.getId() == 0){
            var player = playerRepository.findById(dto.getPlayerId());
            if(player.isEmpty())
                throw new DataNotFoundException();

            playerStatistics = PlayerStatisticsMapper.convertToPlayerStatistics(dto);
            playerStatistics.setPlayer(player.get());
        }
        else
        {
            var playerStatisticOptional = playerStatisticsRepository.findById(dto.getId());
            if(playerStatisticOptional.isEmpty())
                throw new DataNotFoundException();

            playerStatistics = playerStatisticOptional.get();
            playerStatistics.setGoals(dto.getGoals());
            playerStatistics.setAssists(dto.getAssists());
            playerStatistics.setSeason(dto.getSeason());
        }

        try{
            return PlayerStatisticsMapper.convertToDto(playerStatisticsRepository.save(playerStatistics));
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataAlreadyExistsException();
        }
    }

    @Override
    public PlayerStatisticsDto getPlayerStatistics(Integer playerId, String season) {
        var playerStatistics = playerStatisticsRepository.getPlayerStatisticsBySeason(playerId, season);
        if(playerStatistics == null)
                throw new DataNotFoundException();

        return PlayerStatisticsMapper
                .convertToDto(playerStatistics);
    }
}
