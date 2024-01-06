package com.unibuc.FTR.dto;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.validator.OnlyLetters;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    private String name;

    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    private String coach;

    private String description;
}
