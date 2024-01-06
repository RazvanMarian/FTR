package com.unibuc.FTR.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fixtures")
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="home_team_id", nullable=false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name="away_team_id", nullable=false)
    private Team awayTeam;

    private Date date;

    private String stadium;
}
