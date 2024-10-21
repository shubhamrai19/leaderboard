package com.shubham.leaderboard.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int gameId;
    private String gameName;
    private int maxPlayer;
    private int maxDuration;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
