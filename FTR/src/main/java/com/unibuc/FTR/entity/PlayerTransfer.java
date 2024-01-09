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
@Table(name = "PlayerTransfers")
public class PlayerTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="from_team_id", nullable=false)
    private Team fromTeam;

    @ManyToOne
    @JoinColumn(name="to_team_id", nullable=false)
    private Team toTeam;

    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    private Float sum;

    private Date date;
}
