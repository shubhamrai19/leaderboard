package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player();
        player.setPlayerId(1);
        player.setName("Test Player");
    }

    @Test
    void createPlayer_success() {
        when(playerService.createPlayer(player)).thenReturn(player);

        ResponseEntity<?> response = playerController.createPlayer(player);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(player, response.getBody());
    }

    @Test
    void createPlayer_failure() {
        when(playerService.createPlayer(any(Player.class))).thenThrow(new RuntimeException("Failed to create player"));

        ResponseEntity<?> response = playerController.createPlayer(player);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Failed to create player", response.getBody());
    }


    @Test
    void getPlayerById_success() {
        when(playerService.getPlayerId(1)).thenReturn(java.util.Optional.of(player));

        ResponseEntity<?> response = playerController.getPlayerById(1);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(player, response.getBody());
    }

    @Test
    void getPlayerById_notFound() {
        when(playerService.getPlayerId(1)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = playerController.getPlayerById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
