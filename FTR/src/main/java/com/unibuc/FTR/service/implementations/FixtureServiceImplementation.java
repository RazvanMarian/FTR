package com.unibuc.FTR.service.implementations;

import com.unibuc.FTR.dto.TeamFixtureDto;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.FixtureRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.abstractions.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixtureServiceImplementation implements FixtureService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    @Override
    public List<TeamFixtureDto> getTeamFixtures(String teamName) {
        var team = teamRepository.findByName(teamName);
        if(team == null)
            throw new DataNotFoundException();

        return fixtureRepository.getTeamFixtures(team.getId());
    }
}
