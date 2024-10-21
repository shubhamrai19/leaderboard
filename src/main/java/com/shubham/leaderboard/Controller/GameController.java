package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {



    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        try {
            Game createdGame = gameService.createGame(game);
            return new ResponseEntity<>(createdGame, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        Optional<Game> game = gameService.getGameById(gameId);
        return game.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable int gameId, @RequestBody Game game) {
        if (gameId != game.getGameId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Game updatedGame = gameService.updateGame(game);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
