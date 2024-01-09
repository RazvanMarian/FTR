package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.PlayerTransferDto;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.PlayerTransfer;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.PlayerTransferRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.implementations.PlayerTransfersServiceImpl;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
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
public class PlayerTransferServiceSaveTest {
    @InjectMocks
    PlayerTransfersServiceImpl playerTransfersService;

    @Mock
    PlayerTransferRepository playerTransferRepository;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    TeamRepository teamRepository;

    @SneakyThrows
    @Test
    @DisplayName("Create player transfer - happy path")
    public void create()
    {
        Team testTeamTo = new Team(1,"TestTeamTo","TestCoach", "TestDescription", null, null, null);
        Team testTeamFrom = new Team(2,"TestTeamFrom","TestCoach", "TestDescription", null, null, null);
        Player player = new Player(1, "test", "test",new Date(),"defender", null, new HashSet<>());

        PlayerTransferDto playerTransferTest = new PlayerTransferDto(2,1,1,70.5f,"15/11/2023");
        PlayerTransfer playerTransfer = new PlayerTransfer(1, testTeamFrom, testTeamTo, player, 70.5f, new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2023"));

        when(teamRepository.findById(1)).thenReturn(Optional.of(testTeamTo));
        when(teamRepository.findById(2)).thenReturn(Optional.of(testTeamFrom));
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerTransferRepository.save(any(PlayerTransfer.class))).thenReturn(playerTransfer);

        var resultDto = playerTransfersService.save(playerTransferTest);

        verify(teamRepository).findById(1);
        verify(teamRepository).findById(2);
        verify(playerRepository).findById(1);
        verify(playerTransferRepository).save(any(PlayerTransfer.class));

        assertThat(resultDto).isEqualTo(playerTransferTest);
    }

    @Test
    @DisplayName("Create player transfer - unhappy path team not found")
    public void createFails()
    {
        PlayerTransferDto playerTransferTest = new PlayerTransferDto(1,2,1,70.5f,"15/11/2023");

        when(teamRepository
                .findById(ArgumentMatchers.any(Integer.class)))
                .thenThrow(new DataNotFoundException());

        assertThatThrownBy(() -> playerTransfersService.save(playerTransferTest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);

        verify(teamRepository).findById(ArgumentMatchers.any(Integer.class));
    }

}
