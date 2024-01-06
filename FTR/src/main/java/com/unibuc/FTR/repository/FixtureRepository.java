package com.unibuc.FTR.repository;

import com.unibuc.FTR.dto.TeamFixtureDto;
import com.unibuc.FTR.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FixtureRepository extends JpaRepository<Fixture, Integer> {

    @Query(value = "SELECT TH.name as homeTeam, TA.name as awayTeam, F.date as date FROM fixtures F " +
            "INNER JOIN teams TH " +
            "ON TH.id = F.home_team_id " +
            "INNER JOIN teams TA " +
            "ON TA.id = F.away_team_id " +
            "WHERE home_team_id=?1 or away_team_id=?1 " +
            "ORDER BY F.date",nativeQuery = true)

    public List<TeamFixtureDto> getTeamFixtures(Integer teamId);
}
