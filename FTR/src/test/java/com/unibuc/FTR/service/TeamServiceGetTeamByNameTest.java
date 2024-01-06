package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.implementations.TeamServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TeamServiceGetTeamByNameTest {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImplementation teamService;

    @Test
    @DisplayName("Get team by name - happy path")
    public void GetTeamByName()
    {
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);

        when(teamRepository.findByName("TestTeam")).thenReturn(team);

        TeamDto resultDto = teamService.getTeamByName("TestTeam");

        verify(teamRepository).findByName("TestTeam");
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getName()).isEqualTo("TestTeam");
    }

    @Test
    @DisplayName("Get team by name - unhappy path")
    public void GetTeamByNameFails()
    {
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);

        when(teamRepository.findByName(anyString())).thenThrow(new DataNotFoundException());

        assertThatThrownBy(() -> teamService.getTeamByName("SomeTeam"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);

        verify(teamRepository).findByName("SomeTeam");
    }
}
