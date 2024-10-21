package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = playerService.createPlayer(player);
            return ResponseEntity.ok(createdPlayer);
        } catch (Exception e) {
            log.error("Error creating player: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") int id, @RequestBody Player player) {
        try {
            player.setPlayerId(id);
            Player updatedPlayer = playerService.updatePlayer(player);
            return ResponseEntity.ok(updatedPlayer);
        } catch (Exception e) {
            log.error("Error updating player with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") int id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting player with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") int id) {
        try {
            Optional<Player> player = playerService.getPlayerId(id);
            return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error fetching player with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        try {
            List<Player> players = playerService.getAllPlayer();
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            log.error("Error fetching all players: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
