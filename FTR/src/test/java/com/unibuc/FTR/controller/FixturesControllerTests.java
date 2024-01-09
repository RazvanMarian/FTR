package com.unibuc.FTR.controller;

import com.unibuc.FTR.dto.TeamFixtureDto;
import com.unibuc.FTR.service.abstractions.FixtureService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FixturesControllerTests {
    @Mock
    FixtureService fixtureService;

    @InjectMocks
    private FixtureController fixtureController;


    @Test
    @DisplayName("Get team fixtures - happy path")
    public void getTeamFixtures()
    {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        TeamFixtureDto resultDto = factory.createProjection(TeamFixtureDto.class);
        resultDto.setAwayTeam("away team test");
        resultDto.setHomeTeam("home team test");
        resultDto.setDate(new Date());

        when(fixtureService.getTeamFixtures(any(String.class))).thenReturn(List.of(resultDto));

        var responseEntity = fixtureController.GetTeamFixtures("home team test");

        verify(fixtureService).getTeamFixtures("home team test");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(List.of(resultDto));
    }
}
