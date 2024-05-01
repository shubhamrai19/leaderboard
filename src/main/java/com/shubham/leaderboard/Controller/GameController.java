package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/game")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.saveGame(game));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getByID(id));

    }

}
