package com.unibuc.FTR.mapper;

import com.unibuc.FTR.dto.TeamRosterDto;
import com.unibuc.FTR.entity.TeamRoster;

public class TeamRosterMapper {
    public static TeamRosterDto convertToDto(TeamRoster teamRoster){
        return TeamRosterDto.builder()
                .season(teamRoster.getSeason())
                .teamName(teamRoster.getTeam().getName())
                .build();
    }

    public static TeamRoster convertToTeam(TeamRosterDto teamRosterDto){
        return TeamRoster.builder()
                .season(teamRosterDto.getSeason())
                .build();
    }
}
