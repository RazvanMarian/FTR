package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.service.abstractions.PlayerService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTests {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    @DisplayName("Add player - happy path")
    public void addPlayer()
    {
        PlayerDto dto = new PlayerDto(0, "test", "test", "16/11/1999","defender", 1);

        when(playerService.save(any(PlayerDto.class))).thenReturn(dto);

        var responseEntity = playerController.AddPlayer(dto);

        verify(playerService).save(dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(dto);
    }

    @Test
    @DisplayName("Add player to roster - happy path")
    public void addPlayerToRoster()
    {
        PlayerDto dto = new PlayerDto(0, "test", "test", "16/11/1999","defender", 1);

        when(playerService.addPlayerToRoster(1, 1)).thenReturn(dto);

        var responseEntity = playerController.AddPlayerToRoster(1, 1);

        verify(playerService).addPlayerToRoster(1, 1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(dto);
    }

    @Test
    @DisplayName("Delete player from roster - happy path")
    public void deletePlayerFromRoster()
    {
        PlayerDto dto = new PlayerDto(0, "test", "test", "16/11/1999","defender", null);

        when(playerService.deletePlayerFromRoster(1)).thenReturn(dto);

        var responseEntity = playerController.DeletePlayerFromRoster(1);

        verify(playerService).deletePlayerFromRoster(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(dto);
    }
}
