package com.shubham.leaderboard.Service;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.dto.SignInRequest;
import com.shubham.leaderboard.dto.SignInResponse;
import com.shubham.leaderboard.dto.SignUpRequest;

public interface AuthService {

    User signup(SignUpRequest Request);

    SignInResponse signIn(SignInRequest Request);
}
