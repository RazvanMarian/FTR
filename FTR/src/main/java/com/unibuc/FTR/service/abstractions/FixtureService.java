package com.unibuc.FTR.service.abstractions;

import com.unibuc.FTR.dto.TeamFixtureDto;

import java.util.List;

public interface FixtureService {
    public List<TeamFixtureDto> getTeamFixtures(String teamName);
}
