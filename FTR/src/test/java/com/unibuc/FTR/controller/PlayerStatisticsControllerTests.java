package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerStatisticsDto;
import com.unibuc.FTR.service.abstractions.PlayerStatisticsService;
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
public class PlayerStatisticsControllerTests {

    @Mock
    private PlayerStatisticsService playerStatisticsService;

    @InjectMocks
    private PlayerStatisticsController playerStatisticsController;


    @Test
    @DisplayName("Add player statistics - happy path")
    public void addPlayerStatistics()
    {
        PlayerStatisticsDto dto = new PlayerStatisticsDto(0, 1,10 ,5, "2023/2024");

        when(playerStatisticsService.save(any(PlayerStatisticsDto.class))).thenReturn(dto);

        var responseEntity = playerStatisticsController.AddStatistics(dto);

        verify(playerStatisticsService).save(dto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(dto);
    }

    @Test
    @DisplayName("Get player statistics - happy path")
    public void getPlayerStatistics()
    {
        PlayerStatisticsDto dto = new PlayerStatisticsDto(0, 1,10 ,5, "2023/2024");

        when(playerStatisticsService.getPlayerStatistics(1, "2023/2024")).thenReturn(dto);

        var responseEntity = playerStatisticsController.GetPlayerStatisticsBySeason(1, "2023/2024");

        verify(playerStatisticsService).getPlayerStatistics(1, "2023/2024");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(dto);
    }

}
