package com.unibuc.FTR.repository;

import com.unibuc.FTR.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT * FROM players WHERE teamroaster_id=?",nativeQuery = true)
    public Set<Player> findByTeamRosterId(Integer teamRosterId);
}
