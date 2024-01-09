package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.TeamRosterDto;
import com.unibuc.FTR.service.abstractions.TeamRosterService;
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
public class TeamRosterControllerTests {

    @Mock
    TeamRosterService teamRosterService;

    @InjectMocks
    private TeamRosterController teamRosterController;


    @Test
    @DisplayName("Create team roster - happy path")
    public void create()
    {
        TeamRosterDto teamRosterDto = new TeamRosterDto("Barcelona", "2023/2024");

        when(teamRosterService.save(any(TeamRosterDto.class))).thenReturn(teamRosterDto);

        var responseEntity = teamRosterController.AddTeam(teamRosterDto);

        verify(teamRosterService).save(teamRosterDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(teamRosterDto);
    }
}
