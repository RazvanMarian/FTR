package com.unibuc.FTR.dto;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.validator.OnlyLetters;
import com.unibuc.FTR.validator.SeasonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRosterDto {

    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    @OnlyLetters()
    private String teamName;

    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    @SeasonFormat()
    private String season;
}
