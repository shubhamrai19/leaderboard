package com.shubham.leaderboard.Repository.Player;

import com.shubham.leaderboard.Model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository

public class PlayerRepository implements JdbcPlayerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int savePlayer(Player player) {
        try {
            return jdbcTemplate.update("insert into Player(name,email,mobile_number) values(?,?,?)",
                    player.name(), player.email(), player.mobile_number()
            );
        } catch (Exception e) {
            log.error("Exception while saving player", e);
            throw new RuntimeException("error while saving data");
        }
    }

    @Override
    public Player getByID(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from Player where id=?",
                    (rs, rowNum) -> {
                        return new Player(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("mobile_number")
                        );
                    }
                    , id);
        } catch (Exception exec) {
            log.error("exception while getting player data", exec);
            throw new RuntimeException("error while getting data");
        }
    }

//    @Override
    public Player get(String s) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Player WHERE name = ?",
                    (rs, rowNum) -> {
                        return new Player(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("mobile_number")
                        );
                    }
                    , s);
        } catch (Exception exec) {
            log.error("exception while getting player data by name", exec);
            throw new RuntimeException("error while getting data by name");
        }
    }


}
