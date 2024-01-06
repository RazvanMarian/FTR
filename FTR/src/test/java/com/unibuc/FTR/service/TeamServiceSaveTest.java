package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.TeamDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataAlreadyExistsException;
import com.unibuc.FTR.mapper.TeamMapper;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.implementations.TeamServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TeamServiceSaveTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImplementation teamService;

    @Test
    @DisplayName("Create team - happy path")
    public void create()
    {
        TeamDto teamDtoTest = new TeamDto("TestTeam", "TestCoach", "TestDescription");
        Team teamToSave = TeamMapper.convertToTeam(teamDtoTest);
        Team savedTeam = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);

        when(teamRepository.save(ArgumentMatchers.any(Team.class))).thenReturn(savedTeam);

        TeamDto resultDto = teamService.save(teamDtoTest);

        verify(teamRepository).save(teamToSave);
        assertThat(resultDto).isEqualTo(teamDtoTest);
    }

    @Test
    @DisplayName("Create team - unhappy path")
    public void createFails()
    {
        TeamDto teamDtoTest = new TeamDto("TestTeam", "TestCoach", "TestDescription");
        Team teamToSave = TeamMapper.convertToTeam(teamDtoTest);

        when(teamRepository
                .save(ArgumentMatchers.any(Team.class)))
                .thenThrow(new DataIntegrityViolationException(""));

        assertThatThrownBy(() -> teamService.save(teamDtoTest))
                .isInstanceOf(DataAlreadyExistsException.class)
                .hasMessage(Constants.DATA_ALREADY_EXISTS);

        verify(teamRepository).save(teamToSave);
    }
}
