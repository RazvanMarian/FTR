package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.PlayerStatisticsDto;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.PlayerStatistics;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.PlayerStatisticsMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.PlayerStatisticsRepository;
import com.unibuc.FTR.service.implementations.PlayerStatisticsServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlayerStatisticsSaveTest {
    @Mock
    private PlayerStatisticsRepository playerStatisticsRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerStatisticsServiceImplementation playerStatisticsService;

    @Test
    @DisplayName("Save player statistics - happy path")
    public void create()
    {
        PlayerStatisticsDto playerStatisticsDtoTest = new PlayerStatisticsDto(0, 1, 10, 5, "2023/2024");
        PlayerStatistics playerStatisticsToSave = PlayerStatisticsMapper.convertToPlayerStatistics(playerStatisticsDtoTest);
        Player player = new Player(1, "test", "test",new Date(),"defender", null, new HashSet<>());
        playerStatisticsToSave.setPlayer(player);

        when(playerRepository.findById(any())).thenReturn(Optional.of(player));
        when(playerStatisticsRepository.save(ArgumentMatchers.any(PlayerStatistics.class))).thenReturn(playerStatisticsToSave);

        PlayerStatisticsDto resultDto = playerStatisticsService.save(playerStatisticsDtoTest);

        verify(playerStatisticsRepository).save(playerStatisticsToSave);
        assertThat(resultDto).isEqualTo(playerStatisticsDtoTest);
    }

    @Test
    @DisplayName("Save player statistics - unhappy path player doesn't exist")
    public void createFails()
    {
        PlayerStatisticsDto playerStatisticsDtoTest = new PlayerStatisticsDto(0, 1, 10, 5, "2023/2024");

        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerStatisticsService.save(playerStatisticsDtoTest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);
    }
}
