package com.unibuc.FTR.repository;

import com.unibuc.FTR.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    @Query(value = "SELECT * FROM teams WHERE name=?",nativeQuery = true)
    public Team findByName(String name);
}
