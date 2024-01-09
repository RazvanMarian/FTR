package com.unibuc.FTR.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PlayerStatistics")
public class PlayerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer goals;

    private Integer assists;

    private String season;

    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
    private Player player;
}
