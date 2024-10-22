package com.shubham.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LeaderboardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LeaderboardApplication.class, args);
	}

}
