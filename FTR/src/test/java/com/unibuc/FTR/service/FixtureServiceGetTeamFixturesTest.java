package com.unibuc.FTR.service;

import com.unibuc.FTR.constants.Constants;
import com.unibuc.FTR.dto.TeamFixtureDto;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.exception.DataNotFoundException;
import com.unibuc.FTR.repository.FixtureRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.service.implementations.FixtureServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FixtureServiceGetTeamFixturesTest {

    @Mock
    private FixtureRepository fixtureRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private FixtureServiceImpl fixtureService;


    @Test
    @DisplayName("Get team fixtures - happy path")
    public void getTeamFixtures()
    {
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        TeamFixtureDto resultDto = factory.createProjection(TeamFixtureDto.class);
        resultDto.setAwayTeam("away team test");
        resultDto.setHomeTeam("home team test");
        resultDto.setDate(new Date());

        when(teamRepository.findByName("TestTeam")).thenReturn(team);
        when(fixtureRepository.getTeamFixtures(team.getId())).thenReturn(List.of(resultDto));

        var result = fixtureService.getTeamFixtures("TestTeam");

        verify(teamRepository).findByName(any());
        verify(fixtureRepository).getTeamFixtures(team.getId());
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getHomeTeam()).isEqualTo("home team test");
    }

    @Test
    @DisplayName("Get team fixtures - unhappy path team doesn't exist")
    public void getTeamFixturesFails()
    {
        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);

        when(teamRepository.findByName("Test")).thenReturn(null);
        assertThatThrownBy(() -> fixtureService.getTeamFixtures("Test"))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessage(Constants.DATA_NOT_FOUND);
        verify(teamRepository).findByName(any());
    }
}
