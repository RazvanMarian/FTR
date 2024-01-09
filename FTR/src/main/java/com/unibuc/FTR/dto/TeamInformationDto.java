package com.unibuc.FTR.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamInformationDto {
    private String name;

    private List<PlayerDto> players;

    private String rosterSeason;

    private String coach;

    private String description;
}
