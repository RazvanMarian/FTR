package com.unibuc.FTR.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String position;

    @ManyToOne
    @JoinColumn(name="teamroaster_id")
    private TeamRoster teamRoster;

    @OneToMany(mappedBy = "player")
    private Set<PlayerStatistics> statistics;
}
