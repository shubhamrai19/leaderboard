package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody Score score) {
        try {
            Score createdScore = scoreService.create(score);
            return ResponseEntity.ok(createdScore);
        } catch (Exception e) {
            log.error("Error creating score: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(@PathVariable("id") int id, @RequestBody Score score) {
        try {
            score.setScoreId(id);
            Score updatedScore = scoreService.update(score);
            return ResponseEntity.ok(updatedScore);
        } catch (Exception e) {
            log.error("Error updating score with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable("id") int id) {
        try {
            scoreService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting score with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable("id") int id) {
        try {
            Optional<Score> score = scoreService.getId(id);
            return score.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error fetching score with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        try {
            List<Score> scores = scoreService.getAllPlayer();
            return ResponseEntity.ok(scores);
        } catch (Exception e) {
            log.error("Error fetching all scores: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
