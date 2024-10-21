package com.shubham.leaderboard.Repository.impl;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlayerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Player> playerRowMapper = new RowMapper<>() {
        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Player(
                    rs.getInt("playerId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("mobileNumber"),
                    rs.getTimestamp("createdAt"),
                    rs.getTimestamp("updatedAt")
            );
        }
    };

    @Override
    public int save(Player player) {
        String sql = "INSERT INTO player(name,email,mobileNumber,createdAt) values(?,?,?,?)";
        try {
            return jdbcTemplate.update(sql, player.getName(), player.getEmail(), player.getMobileNumber(), new Timestamp(System.currentTimeMillis()));
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("Player with name '" + player.getName() + "' already exists!");
            } else {
                throw new RuntimeException("An error occurred while saving the player: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the player: " + e.getMessage());
        }
    }

    @Override
    public int update(Player player) {
        String sql = "UPDATE player SET name=?,email=?,mobileNumber=?,updatedAt=?";

        try {
            return jdbcTemplate.update
                    (sql, player.getName(), player.getEmail(), player.getMobileNumber(), new Timestamp(System.currentTimeMillis()),
                            player.getPlayerId());
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("Player with name '" + player.getName() + "' already exists!");
            } else {
                throw new RuntimeException("An error occurred while updating the player: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the player: " + e.getMessage());
        }

    }

    @Override
    public int delete(int playerId) {
        String sql = "DELETE FROM player where playerId=?";
        try {
            return jdbcTemplate.update(sql, playerId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the player: " + e.getMessage());
        }
    }

    @Override
    public Optional<Player> findById(int playerId) {
        String sql = "SELECT  * FROM player where playerId=?";
        try {
            return jdbcTemplate.query(sql, playerRowMapper, playerId).stream().findFirst();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the player: " + e.getMessage());
        }
    }

    @Override
    public List<Player> findAll() {
        String sql="SELECT * FROM player";
        try {
            return jdbcTemplate.query(sql,playerRowMapper);
        }catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the players: " + e.getMessage());
        }
    }
}
