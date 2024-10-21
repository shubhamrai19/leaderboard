package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ScoreControllerTest {

    @Mock
    private ScoreService scoreService;

    @InjectMocks
    private ScoreController scoreController;

    private Score score;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        score = new Score();
        score.setScoreId(1);
        score.setScored_value(11);
        score.setGameId(1);
        score.setPlayer_id(1);
    }

    @Test
    void createScore_success() {
        when(scoreService.create(score)).thenReturn(score);

        ResponseEntity<?> response = scoreController.createScore(score);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(score, response.getBody());
    }

    @Test
    void createScore_failure() {
        when(scoreService.create(any(Score.class))).thenThrow(new RuntimeException("Failed to create score"));

        ResponseEntity<?> response = scoreController.createScore(score);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Failed to create score", response.getBody());
    }


    @Test
    void getPlayerById_success() {
        when(scoreService.getId(1)).thenReturn(java.util.Optional.of(score));

        ResponseEntity<?> response = scoreController.getScoreById(1);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(score, response.getBody());
    }

    @Test
    void getPlayerById_notFound() {
        when(scoreService.getId(1)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = scoreController.getScoreById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
