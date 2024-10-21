package com.shubham.leaderboard.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int playerId;
    private String name;
    private String email;
    private String mobileNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}

