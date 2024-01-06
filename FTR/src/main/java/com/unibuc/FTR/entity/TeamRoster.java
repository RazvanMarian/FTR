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
@Table(name = "TeamRosters", uniqueConstraints={@UniqueConstraint(columnNames = {"team_id","season"})})
public class TeamRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    @OneToMany(mappedBy = "teamRoster")
    private Set<Player> players;

    private String season;
}
