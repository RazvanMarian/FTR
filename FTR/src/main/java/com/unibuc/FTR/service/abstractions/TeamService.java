package com.unibuc.FTR.service.abstractions;

import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.dto.TeamInformationDto;

import java.util.List;

public interface TeamService extends GenericService<TeamDto>{
    public List<TeamDto> getAllTeams();
    public TeamDto getTeamByName(String name);
    public TeamInformationDto getTeamInformation(Integer teamId, String season);
}
