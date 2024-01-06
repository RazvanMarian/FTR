package com.unibuc.FTR.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamInformationDto {
    private String name;

    private List<PlayerDto> players;

    private String rosterSeason;

    private String coach;

    private String description;
}
