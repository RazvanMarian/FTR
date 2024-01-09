package com.unibuc.FTR.mapper;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.entity.Player;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlayerMapper {
    public static PlayerDto convertToDto(Player player){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return PlayerDto.builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .birthDay(dateFormat.format(player.getBirthDay()))
                .rosterId(player.getTeamRoster() == null ? null : player.getTeamRoster().getId())
                .position(player.getPosition())
                .id(player.getId())
                .build();
    }

    public static Player convertToPlayer(PlayerDto playerDto) {
        try {
            return Player.builder()
                    .firstName(playerDto.getFirstName())
                    .lastName(playerDto.getLastName())
                    .birthDay(new SimpleDateFormat("dd/MM/yyyy").parse(playerDto.getBirthDay()))
                    .position(playerDto.getPosition())
                    .id(playerDto.getId())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
