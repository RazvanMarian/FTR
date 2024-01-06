package com.unibuc.FTR.dto;

import java.util.Date;

public interface TeamFixtureDto {

    Date getDate();

    String getHomeTeam();

    String getAwayTeam();

    void setDate(Date date);
    void setHomeTeam(String homeTeam);
    void setAwayTeam(String awayTeam);
}
