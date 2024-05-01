package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.savePlayer(player));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getBy(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getBy(id));
    }


}
