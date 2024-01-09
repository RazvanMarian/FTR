package com.unibuc.FTR.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTransferDto {
    private Integer fromTeamId;

    private Integer toTeamId;

    private Integer playerId;

    private float sum;

    private String date;
}
