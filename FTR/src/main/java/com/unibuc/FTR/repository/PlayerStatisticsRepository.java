package com.unibuc.FTR.repository;

import com.unibuc.FTR.entity.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerStatisticsRepository extends JpaRepository<PlayerStatistics, Integer> {

    @Query(value = "SELECT * FROM player_statistics WHERE player_id=?1 AND season=?2",nativeQuery = true)
    PlayerStatistics getPlayerStatisticsBySeason(Integer playerId, String season);
}
