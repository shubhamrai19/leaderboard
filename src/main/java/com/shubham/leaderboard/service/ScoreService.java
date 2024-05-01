package com.shubham.leaderboard.service;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Repository.Score.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    public Score saveScore(Score score,int id) {
      scoreRepository.saveScore(score,id );
        return scoreRepository.getById(score.player_id());
      //  return score;
    }
}
