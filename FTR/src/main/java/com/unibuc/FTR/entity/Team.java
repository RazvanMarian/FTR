package com.unibuc.FTR.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Teams", uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String coach;

    private String description;

    @OneToMany(mappedBy = "team")
    private Set<TeamRoster> rosters;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Fixture> homeFixtures;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Fixture> awaitFixtures;
}
