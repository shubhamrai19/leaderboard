package com.shubham.leaderboard.Repository.impl;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Repository.ScoreRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ScoreRepositoryImpl implements ScoreRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScoreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public final RowMapper<Score> rowMapperS = new RowMapper<>() {
        @Override
        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Score(
                    rs.getInt("scoreId"),
                    rs.getInt("player_id"),
                    rs.getInt("scored_value"),
                    rs.getInt("gameId"),
                    rs.getTimestamp("createdAt")
            );
        }
    };

    @Override
    public int save(Score score) {
        String sql = "INSERT INTO score (player_id, scored_value, gameId, createdAt) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                score.getPlayer_id(),
                score.getScored_value(),
                score.getGameId(),
                score.getCreatedAt());
    }

    @Override
    public int update(Score score) {
        String sql = "UPDATE score SET player_id = ?, scored_value = ?, gameId = ?, createdAt = ? WHERE scoreId = ?";
        return jdbcTemplate.update(sql,
                score.getPlayer_id(),
                score.getScored_value(),
                score.getGameId(),
                score.getCreatedAt(),
                score.getScoreId());
    }

    @Override
    public int delete(int scoreId) {
        String sql = "DELETE FROM score WHERE scoreId = ?";
        return jdbcTemplate.update(sql, scoreId);
    }

    @Override
    public Optional<Score> findById(int scoreId) {
        String sql = "SELECT * FROM score WHERE scoreId = ?";
        return jdbcTemplate.query(sql, rowMapperS, scoreId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Score> findAll() {
        String sql = "SELECT * FROM score";
        return jdbcTemplate.query(sql, rowMapperS);
    }
}
