package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.TeamRosterDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.entity.TeamRoster;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.mapper.TeamRosterMapper;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.implementations.TeamRosterServiceImplementation;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TeamRosterServiceSaveTest {
    @Mock
    private TeamRosterRepository teamRosterRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamRosterServiceImplementation teamRosterService;

    @Test
    @DisplayName("Create team roster - happy path")
    public void create()
    {
        TeamRosterDto teamRosterDtoToTest = new TeamRosterDto("TestTeam", "2023/2024");
        TeamRoster teamRosterToSave = TeamRosterMapper.convertToTeam(teamRosterDtoToTest);
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);
        teamRosterToSave.setTeam(team);

        TeamRoster savedTeamRoster = new TeamRoster(1,team,null,"2023/2024");

        when(teamRosterRepository.save(ArgumentMatchers.any(TeamRoster.class))).thenReturn(savedTeamRoster);
        when(teamRepository.findByName("TestTeam")).thenReturn(team);

        TeamRosterDto resultDto = teamRosterService.save(teamRosterDtoToTest);

        verify(teamRosterRepository).save(teamRosterToSave);
        assertThat(resultDto).isEqualTo(teamRosterDtoToTest);
    }

    @Test
    @DisplayName("Create team roster - unhappy path")
    public void createFailsTeamNotFound()
    {
        TeamRosterDto teamRosterDtoToTest = new TeamRosterDto("TestTeam", "2023/2024");
        when(teamRepository.findByName(any())).thenThrow(new DataNotFoundException());

        assertThatThrownBy(() -> teamRosterService.save(teamRosterDtoToTest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);

    }

    @Test
    @DisplayName("Create team roster - unhappy path roster already exists")
    public void createFailsRosterTeamAlreadyExists()
    {
        TeamRosterDto teamRosterDtoToTest = new TeamRosterDto("TestTeam", "2023/2024");
        TeamRoster teamRosterToSave = TeamRosterMapper.convertToTeam(teamRosterDtoToTest);
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);
        teamRosterToSave.setTeam(team);

        when(teamRosterRepository.save(ArgumentMatchers.any(TeamRoster.class))).thenThrow(new DataIntegrityViolationException(""));
        when(teamRepository.findByName("TestTeam")).thenReturn(team);


        assertThatThrownBy(() -> teamRosterService.save(teamRosterDtoToTest))
                .isInstanceOf(DataAlreadyExistsException.class)
                .hasMessage(Constants.DATA_ALREADY_EXISTS);
    }
}
