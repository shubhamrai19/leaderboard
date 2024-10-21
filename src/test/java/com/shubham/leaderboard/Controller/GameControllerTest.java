package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new Game();
        game.setGameId(1);
        game.setGameName("Test Game");
    }

    @Test
    void createGame_success() {
        when(gameService.createGame(game)).thenReturn(game);

        ResponseEntity<?> response = gameController.createGame(game);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(game, response.getBody());
    }

    @Test
    void createGame_failure() {
        when(gameService.createGame(any(Game.class))).thenThrow(new RuntimeException("Failed to create game"));

        ResponseEntity<?> response = gameController.createGame(game);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Failed to create game", response.getBody());
    }


    @Test
    void getGameById_success() {
        when(gameService.getGameById(1)).thenReturn(java.util.Optional.of(game));

        ResponseEntity<?> response = gameController.getGameById(1);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(game, response.getBody());
    }

    @Test
    void getGameById_notFound() {
        when(gameService.getGameById(1)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = gameController.getGameById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
