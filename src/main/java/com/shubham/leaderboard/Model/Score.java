package com.shubham.leaderboard.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private int scoreId;
    private int player_id;
    private int scored_value;
    private int gameId;
    private Timestamp createdAt;

}
