package com.unibuc.FTR.repository;

import com.unibuc.FTR.entity.TeamRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRosterRepository extends JpaRepository<TeamRoster, Integer> {

    @Query(value = "SELECT * FROM team_rosters WHERE team_id=?",nativeQuery = true)
    public List<TeamRoster> findByTeamId(Integer teamId);
}
