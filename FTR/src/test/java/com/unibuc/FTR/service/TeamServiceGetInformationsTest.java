package com.unibuc.FTR.service;

import com.unibuc.FTR.dto.TeamInformationDto;
import com.unibuc.FTR.entity.Player;
import com.unibuc.FTR.entity.Team;
import com.unibuc.FTR.entity.TeamRoster;
import com.unibuc.FTR.repository.PlayerRepository;
import com.unibuc.FTR.repository.TeamRepository;
import com.unibuc.FTR.repository.TeamRosterRepository;
import com.unibuc.FTR.service.implementations.TeamServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TeamServiceGetInformationsTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamRosterRepository teamRosterRepository;

    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private TeamServiceImpl teamService;


    @Test
    @DisplayName("Get informations - happy path")
    public void GetInformations()
    {

        List<TeamRoster> rosters = Arrays.asList(
                new TeamRoster(1, null, null, "2023/2024"),
                new TeamRoster(2, null, null, "2022/2023")
        );
        when(teamRosterRepository.findByTeamId(anyInt())).thenReturn(rosters);

        List<Player> players = Arrays.asList(
                new Player(1, "testFirstName", "testLastName", new Date(), "defender", rosters.get(0), null),
                new Player(2, "testFirstName2", "testLastName2", new Date(), "midfielder", rosters.get(0), null)
        );
        when(playerRepository.findByTeamRosterId(anyInt())).thenReturn(new HashSet<>(players));

        Team team = new Team(1,"TestTeam","TestCoach", "TestDescription", null, null, null);
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(team));

        TeamInformationDto resultDto = teamService.getTeamInformation(1, "2023/2024");
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getRosterSeason()).isEqualTo("2023/2024");
        assertThat(resultDto.getName()).isEqualTo("TestTeam");
        assertThat(resultDto.getPlayers().size()).isEqualTo(2);
    }
}
