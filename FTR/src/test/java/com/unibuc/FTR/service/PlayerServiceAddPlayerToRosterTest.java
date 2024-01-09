package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.TeamRoster;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.implementations.PlayerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceAddPlayerToRosterTest {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRosterRepository teamRosterRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("Add player to roster - happy path")
    public void addPlayerToRoster()
    {
        Player player = new Player(1, "test", "test", new Date(),"defender",null, null);
        TeamRoster teamRoster = new TeamRoster(1, null, null, "2023/2024");

        when(teamRosterRepository.findById(1)).thenReturn(Optional.of(teamRoster));
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        var result = playerService.addPlayerToRoster(1, 1);

        verify(teamRosterRepository).findById(1);
        verify(playerRepository).findById(1);
        verify(playerRepository).save(any(Player.class));

        assertThat(result.getRosterId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Add player to roster - unhappy path player doesnt exist")
    public void addPlayerToRosterFails()
    {
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> playerService.addPlayerToRoster(1, 1))
                .isInstanceOf(DataNotFoundException.class)
                        .hasMessage(Constants.DATA_NOT_FOUND);

        verify(playerRepository).findById(1);
    }
}
