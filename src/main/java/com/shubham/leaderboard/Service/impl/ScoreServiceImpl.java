package com.shubham.leaderboard.Service.impl;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Repository.ScoreRepository;
import com.shubham.leaderboard.Service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score create(Score score) {
        try {
            log.info("Creating new score: {}", score);
            scoreRepository.save(score); // Save the new score
            return score;
        } catch (Exception e) {
            log.error("Error creating score: {}", e.getMessage());
            throw new RuntimeException("Failed to create score");
        }
    }

    @Override
    public Score update(Score score) {
        try {
            log.info("Updating score with ID: {}", score.getScoreId());
            Optional<Score> existingScore = scoreRepository.findById(score.getScoreId());

            if (existingScore.isPresent()) {
                scoreRepository.update(score); // Update the score
                return score;
            } else {
                log.warn("Score with ID: {} not found", score.getScoreId());
                throw new RuntimeException("Score not found for update");
            }
        } catch (Exception e) {
            log.error("Error updating score with ID: {}: {}", score.getScoreId(), e.getMessage());
            throw new RuntimeException("Failed to update score");
        }
    }

    @Override
    public void delete(int id) {
        try {
            log.info("Deleting score with ID: {}", id);
            Optional<Score> existingScore = scoreRepository.findById(id);

            if (existingScore.isPresent()) {
                scoreRepository.delete(id); // Delete the score
                log.info("Score with ID: {} deleted successfully", id);
            } else {
                log.warn("Score with ID: {} not found", id);
                throw new RuntimeException("Score not found for deletion");
            }
        } catch (Exception e) {
            log.error("Error deleting score with ID: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete score");
        }
    }

    @Override
    public Optional<Score> getId(int id) {
        try {
            log.info("Fetching score with ID: {}", id);
            return scoreRepository.findById(id);
        } catch (Exception e) {
            log.error("Error fetching score with ID: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch score");
        }
    }

    @Override
    public List<Score> getAllPlayer() {
        try {
            log.info("Fetching all player scores");
            return scoreRepository.findAll();
        } catch (Exception e) {
            log.error("Error fetching all player scores: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all player scores");
        }
    }
}
