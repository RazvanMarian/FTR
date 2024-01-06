package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.PlayerStatistics;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.PlayerStatisticsRepository;
import com.unibuc.FTR.service.implementations.PlayerStatisticsServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlayerStatisticsServiceGetTest {
    @Mock
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerStatisticsServiceImplementation playerStatisticsService;

    @Test
    @DisplayName("Get player statistics - happy path")
    public void GetPlayerStatistics()
    {
        Player player = new Player(1, "test", "test",new Date(),"defender", null, new HashSet<>());
        PlayerStatistics playerStatistics = new PlayerStatistics(1, 10, 5,"2023/2024", player);

        when(playerStatisticsRepository.getPlayerStatisticsBySeason(1, "2023/2024")).thenReturn(playerStatistics);

        var playerStatisticsResult = playerStatisticsService.getPlayerStatistics(1, "2023/2024");

        verify(playerStatisticsRepository).getPlayerStatisticsBySeason(1, "2023/2024");
        assertThat(playerStatisticsResult).isNotNull();
        assertThat(playerStatisticsResult.getGoals()).isEqualTo(10);
    }

    @Test
    @DisplayName("Get player statistics - unhappy path player doesn't exist")
    public void GetPlayerStatisticsFails()
    {
        Player player = new Player(1, "test", "test",new Date(),"defender", null, new HashSet<>());
        PlayerStatistics playerStatistics = new PlayerStatistics(1, 10, 5,"2023/2024", player);

        when(playerStatisticsRepository.getPlayerStatisticsBySeason(2, "2023/2024")).thenReturn(null);

        assertThatThrownBy(() -> playerStatisticsService.getPlayerStatistics(2, "2023/2024"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);

        verify(playerStatisticsRepository).getPlayerStatisticsBySeason(2, "2023/2024");
    }


}
