package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.dto.TeamInformationDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.TeamMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.abstractions.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeamServiceImplementation implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamRosterRepository teamRosterRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public TeamDto save(TeamDto teamDto){
        Team team;
        try{
            team = teamRepository.save(TeamMapper.convertToTeam(teamDto));
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataAlreadyExistsException();
        }

        return TeamMapper.convertToDto(team);
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamRepository
                .findAll()
                .stream()
                .map(TeamMapper::convertToDto)
                .toList();
    }

    @Override
    public TeamDto getTeamByName(String name) {
        var team = teamRepository.findByName(name);
        if(team == null)
            throw new DataNotFoundException();

        return TeamMapper.convertToDto(team);
    }

    @Override
    public TeamInformationDto getTeamInformation(Integer teamId, String season) {
        var teamOptional = teamRepository.findById(teamId);
        if(teamOptional.isEmpty())
            throw new DataNotFoundException();

        var rosters = teamRosterRepository.findByTeamId(teamOptional.get().getId());
        var rosterOptional = rosters.stream().filter(tr -> Objects.equals(tr.getSeason(), season)).findFirst();
        if(rosterOptional.isEmpty())
            throw new DataNotFoundException();

        var roster = rosterOptional.get();
        roster.setPlayers(playerRepository.findByTeamRosterId(roster.getId()));

        return TeamMapper.convertToTeamInformationDto(teamOptional.get(), roster);
    }
}
