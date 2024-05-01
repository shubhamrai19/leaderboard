package com.shubham.leaderboard.Repository.Score;

import com.shubham.leaderboard.Model.Score;

import java.util.List;

public interface JdbcScoreRepository {
   int saveScore(Score score,int id);

   Score getById(int id);

}
