package com.unibuc.FTR.dto;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.validator.OnlyLetters;
import com.unibuc.FTR.validator.SeasonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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
