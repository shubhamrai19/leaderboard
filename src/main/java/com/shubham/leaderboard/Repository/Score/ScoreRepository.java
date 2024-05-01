package com.shubham.leaderboard.Repository.Score;

import com.shubham.leaderboard.Model.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class ScoreRepository implements JdbcScoreRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveScore(Score score, int id) {
        try {
            return jdbcTemplate.update("insert into Score(player_id,scored_value,game_id) values(?,?,?)",
                    score.player_id(), score.scored_value(), id
            );

//            return jdbcTemplate.query("UPDATE Score scored_value=? where player_id=? and game_id=? " +
//                    If(@@ROWCOUNT=0) +
//                    " INSERT into Employees Values ('Ramesh Kumar','ASP.NET,.Net,C#,Xmarin')
//
//            );
//            return jdbcTemplate.update("insert into Score(player_id,scored_value,game_id) values(?,?,?)" +
//                            " ON DUPLICATE KEY UPDATE player_id = VALUES(player_id), scored_value = VALUES(?), game_id = VALUES(?)",
////                    score.player_id(), score.scored_value(), id
////            );
        } catch (Exception e) {
            log.error("Exception while saving score data");
            throw new RuntimeException("error while saving score data");
        }
    }

    @Override
    public Score getById(int id) {
        try {
            return jdbcTemplate.queryForObject("select player_id,scored_value from Score where player_id=? LIMIT 1",
                    (rs, rowNum) -> {
                        return new Score(
                                rs.getInt("player_id"),
                                rs.getInt("scored_value")
                                // rs.getInt("game_id")
                        );
                    }
                    , id);
        } catch (Exception exec) {
            log.error("Exception while getting score data by id");
            throw new RuntimeException("error while getting score data by id");
        }
    }
}
