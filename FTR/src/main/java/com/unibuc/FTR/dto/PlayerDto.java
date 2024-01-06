package com.unibuc.FTR.dto;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.validator.OnlyLetters;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    @NotNull(message = Constants.NOT_BLANK_FORMAT)
    private Integer id;

    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    private String firstName;

    @NotBlank(message = Constants.NOT_BLANK_FORMAT)
    private String lastName;

    private String birthDay;

    private String position;

    private Integer rosterId;
}
