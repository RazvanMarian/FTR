package com.unibuc.FTR.service;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.TeamRoster;
import com.unibuc.FTR.mapper.PlayerMapper;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.implementations.PlayerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceSaveTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRosterRepository teamRosterRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;


    @Test
    @DisplayName("Save player - happy path")
    public void save()
    {
        TeamRoster teamRoster = new TeamRoster(1, null, null, "2023/2024");
        when(teamRosterRepository.findById(1)).thenReturn(Optional.of(teamRoster));

        PlayerDto playerDtoTest = new PlayerDto(0,"test", "test", "16/11/1999", "defender", 1);
        Player playerToSave = PlayerMapper.convertToPlayer(playerDtoTest);
        playerToSave.setTeamRoster(teamRoster);
        when(playerRepository.save(ArgumentMatchers.any(Player.class))).thenReturn(playerToSave);

        var resultDto = playerService.save(playerDtoTest);

        verify(teamRosterRepository).findById(1);
        verify(playerRepository).save(ArgumentMatchers.any(Player.class));
        assertThat(resultDto).isEqualTo(playerDtoTest);
    }

}
