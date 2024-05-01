package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/{id}/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @PostMapping("")
    public ResponseEntity<Score> saveScore(@RequestBody Score score, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scoreService.saveScore(score, id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Score> getBy(@PathVariable int id) {
//        return ResponseEntity.status(HttpStatus.OK).body(scoreService.(id));
//    }

}

//@RequestMapping("/v1/{id}/leaderboard")
//class Leaderboard {
//
//    @GetMapping("")
//    public ResponseEntity<Score> getLeaderboard() {
//        return ResponseEntity.status(HttpStatus.CREATED).body());
//    }

//}
