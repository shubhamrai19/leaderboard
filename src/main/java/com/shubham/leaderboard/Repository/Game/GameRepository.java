package com.shubham.leaderboard.Repository.Game;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Repository.Game.JdbcGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GameRepository implements JdbcGameRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveGame(Game game) {
        try {
           return jdbcTemplate.update("insert into Game(name,max_player,game.duration) values(?,?,?)",
                    game.name(), game.max_player(), game.duration()
            );
        }catch (Exception e){
            log.error("Exception while saving game data",e);
            throw new RuntimeException("error while saving game data");
        }
    }

    @Override
    public Game byGameByID(int id) {
        try {

            return jdbcTemplate.queryForObject("select id,name,max_player,duration from Game where id=?",
                    (rs, rowNum) -> {
                        return new Game(
                                  rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("max_player"),
                                rs.getInt("duration")
                        );
                    }
                    , id);
        }catch (Exception exec){
            log.error("error while getting data",exec);
            throw new RuntimeException("error while getting game data");
        }
    }

    @Override
    public Game byGameByName(String s) {
        try {
            return jdbcTemplate.queryForObject("select id,name,max_player,duration from Game where name=?",
                    (rs, rowNum) ->{
                        return new Game(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("max_player"),
                                rs.getInt("duration")
                        );
                    }
                    ,s);
        }catch (Exception exception){
            log.error("Execption while getting game data by id",exception);
            throw new RuntimeException("error while geeting game data by id");
        }
    }
}
