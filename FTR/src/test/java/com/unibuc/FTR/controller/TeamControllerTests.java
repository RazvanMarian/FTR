package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.PlayerDto;
import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.dto.TeamInformationDto;
import com.unibuc.FTR.service.implementations.TeamServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TeamControllerTests {

    @Mock
    private TeamServiceImplementation teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    @DisplayName("Create team - happy path")
    public void createTeam()
    {
        TeamDto teamDto = new TeamDto("Barcelona", "Guardiola", "");

        when(teamService.save(any(TeamDto.class))).thenReturn(teamDto);

        var responseEntity = teamController.AddTeam(teamDto);

        verify(teamService).save(teamDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(teamDto);
    }

    @Test
    @DisplayName("Get team by name - happy path")
    public void getTeamByName()
    {
        TeamDto teamDto = new TeamDto("Barcelona", "Guardiola", "");

        when(teamService.getTeamByName(any(String.class))).thenReturn(teamDto);

        var responseEntity = teamController.GetTeam("Barcelona");

        verify(teamService).getTeamByName(any(String.class));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(responseEntity.getBody()).isEqualTo(teamDto);
    }

    @Test
    @DisplayName("Get team informations - happy path")
    public void getTeamInformations()
    {
        List<PlayerDto> players = Arrays.asList(
                new PlayerDto(1, "testFirstName", "testLastName", "16/11/1999", "defender", 1),
                new PlayerDto(2, "testFirstName2", "testLastName2", "16/11/1998", "defender", 1)
        );
        TeamInformationDto teamInformationDto =
                new TeamInformationDto("Barcelona", players, "2023/2024", "some coach", "");

        when(teamService.getTeamInformation(1, "2023/2024")).thenReturn(teamInformationDto);

        var responseEntity = teamController.GetInfos(1, "2023/2024");

        verify(teamService).getTeamInformation(1, "2023/2024");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
