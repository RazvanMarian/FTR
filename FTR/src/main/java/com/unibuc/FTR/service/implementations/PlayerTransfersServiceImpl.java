package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.dto.PlayerTransferDto;
import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.entity.PlayerTransfer;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.PlayerTransferMapper;
import com.unibuc.FTR.mapper.TeamMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.PlayerTransferRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.abstractions.PlayerTransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PlayerTransfersServiceImpl implements PlayerTransfersService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerTransferRepository playerTransferRepository;

    @Override
    public PlayerTransferDto save(PlayerTransferDto dto) {
        var fromTeam = teamRepository.findById(dto.getFromTeamId());
        if(fromTeam.isEmpty())
            throw new DataNotFoundException();

        var toTeam = teamRepository.findById(dto.getToTeamId());
        if(toTeam.isEmpty())
            throw new DataNotFoundException();

        var player = playerRepository.findById(dto.getPlayerId());
        if(player.isEmpty())
            throw new DataNotFoundException();

        var playerTransfer = PlayerTransferMapper.convertToPlayerTransfer(dto);
        playerTransfer.setFromTeam(fromTeam.get());
        playerTransfer.setToTeam(toTeam.get());
        playerTransfer.setPlayer(player.get());

        return PlayerTransferMapper.convertToDto(playerTransferRepository.save(playerTransfer));
    }
}
