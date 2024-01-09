package com.unibuc.FTR.mapper;

import com.unibuc.FTR.dto.PlayerTransferDto;
import com.unibuc.FTR.entity.PlayerTransfer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlayerTransferMapper {
    public static PlayerTransferDto convertToDto(PlayerTransfer playerTransfer)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return PlayerTransferDto.builder()
                .sum(playerTransfer.getSum())
                .date(dateFormat.format(playerTransfer.getDate()))
                .fromTeamId(playerTransfer.getFromTeam().getId())
                .toTeamId(playerTransfer.getToTeam().getId())
                .playerId(playerTransfer.getPlayer().getId())
                .build();
    }

    public static PlayerTransfer convertToPlayerTransfer(PlayerTransferDto playerTransfer)
    {
        try {
            return PlayerTransfer.builder()
                    .sum(playerTransfer.getSum())
                    .date(new SimpleDateFormat("dd/MM/yyyy").parse(playerTransfer.getDate()))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
