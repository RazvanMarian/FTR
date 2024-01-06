package com.unibuc.FTR.mapper;

import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.dto.TeamInformationDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.entity.TeamRoster;

public class TeamMapper {

    public static TeamDto convertToDto(Team team){
        return TeamDto.builder()
                .name(team.getName())
                .coach(team.getCoach())
                .description(team.getDescription())
                .build();
    }

    public static Team convertToTeam(TeamDto teamDto){
        return Team.builder()
                .name(teamDto.getName())
                .coach(teamDto.getCoach())
                .description(teamDto.getDescription())
                .build();
    }

    public static TeamInformationDto convertToTeamInformationDto(Team team, TeamRoster roster){
        return TeamInformationDto.builder()
                .name(team.getName())
                .coach(team.getCoach())
                .description(team.getDescription())
                .players(roster.getPlayers().stream().map(PlayerMapper::convertToDto).toList())
                .rosterSeason(roster.getSeason())
                .build();
    }
}
