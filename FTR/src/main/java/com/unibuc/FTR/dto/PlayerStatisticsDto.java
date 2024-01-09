package com.unibuc.FTR.dto;

import com.unibuc.FTR.validator.SeasonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatisticsDto {
    private Integer id;

    private Integer playerId;

    private Integer goals;

    private Integer assists;

    @SeasonFormat()
    private String season;
}
