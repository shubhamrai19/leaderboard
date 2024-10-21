package com.shubham.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private Integer id;
    private String email;
    private String token;
}
