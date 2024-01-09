package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerTransferDto;
import com.unibuc.FTR.service.abstractions.PlayerTransfersService;
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

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PlayerTransferControllerTests {
    @Mock
    PlayerTransfersService playerTransfersService;

    @InjectMocks
    PlayerTransferController playerTransferController;

    @Test
    @DisplayName("Create player transfer - happy path")
    public void create()
    {
        PlayerTransferDto playerTransferTest = new PlayerTransferDto(2,1,1,70.5f,"15/11/2023");

        when(playerTransfersService.save(any(PlayerTransferDto.class))).thenReturn(playerTransferTest);

        var responseEntity = playerTransferController.Add(playerTransferTest);

        verify(playerTransfersService).save(playerTransferTest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(playerTransferTest);
    }
}
