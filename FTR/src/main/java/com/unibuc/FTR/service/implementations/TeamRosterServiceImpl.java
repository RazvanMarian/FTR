package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.dto.TeamRosterDto;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.TeamRosterMapper;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.abstractions.TeamRosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TeamRosterServiceImpl implements TeamRosterService {
    @Autowired
    private TeamRosterRepository teamRosterRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamRosterDto save(TeamRosterDto teamRosterDto) {
        var team = teamRepository.findByName(teamRosterDto.getTeamName());
        if(team == null)
            throw new DataNotFoundException();

        var teamRoster = TeamRosterMapper.convertToTeam(teamRosterDto);
        teamRoster.setTeam(team);

        try{
            return TeamRosterMapper.convertToDto(teamRosterRepository.save(teamRoster));
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataAlreadyExistsException();
        }
    }
}
