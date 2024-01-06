package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.PlayerMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.abstractions.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PlayerServiceImplementation implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRosterRepository teamRosterRepository;

    @Override
    public PlayerDto save(PlayerDto playerDto) {
        var teamRoster = teamRosterRepository.findById(playerDto.getRosterId());
        if(teamRoster.isEmpty())
            throw new DataNotFoundException();

        Player player;
        try {
        if(playerDto.getId() != 0)
        {
            var playerDb = playerRepository.findById(playerDto.getId());
            if(playerDb.isEmpty())
                throw new DataNotFoundException();

            player = playerDb.get();
            player.setTeamRoster(teamRoster.get());
            player.setPosition(playerDto.getPosition());
            player.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse(playerDto.getBirthDay()));
            player.setFirstName(playerDto.getFirstName());
            player.setLastName(playerDto.getLastName());
        }
        else{
            player = PlayerMapper.convertToPlayer(playerDto);
            player.setTeamRoster(teamRoster.get());
        }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return PlayerMapper.convertToDto(playerRepository.save(player));
    }

    @Override
    public void deletePlayerFromRoster(Integer playerId) {
        var player = playerRepository.findById(playerId);
        if(player.isEmpty())
            throw new DataNotFoundException();

        Player entity = player.get();
        entity.setTeamRoster(null);
        playerRepository.save(entity);
    }

    @Override
    public PlayerDto addPlayerToRoster(Integer playerId, Integer rosterId) {
        var playerOptional = playerRepository.findById(playerId);
        if(playerOptional.isEmpty())
            throw new DataNotFoundException();

        var rosterOptional = teamRosterRepository.findById(rosterId);
        if(rosterOptional.isEmpty())
            throw new DataNotFoundException();

        var player = playerOptional.get();
        player.setTeamRoster(rosterOptional.get());

        return PlayerMapper.convertToDto(playerRepository.save(player));
    }
}
