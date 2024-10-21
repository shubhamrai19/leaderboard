package com.shubham.leaderboard.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "email cannot be null")
    private String email;

    @NotBlank(message = "password cannot be null")
    private String password;
}
