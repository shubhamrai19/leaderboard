package com.shubham.leaderboard.Repository.impl;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("id"),
                    rs.getString("emailId"),
                    rs.getString("fullName"),
                    rs.getString("passwords")
            );
        }
    };

    @Override
    public int save(User user) {
        String sql = "INSERT INTO User (emailId, fullName, passwords) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getEmailId(),
                user.getFullName(),
                user.getPasswords());
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE User SET emailId = ?, fullName = ?, passwords = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                user.getEmailId(),
                user.getFullName(),
                user.getPasswords(),
                user.getId());
    }

    @Override
    public int delete(int userId) {
        String sql = "DELETE FROM User WHERE id = ?";
        return jdbcTemplate.update(sql, userId);
    }

    @Override
    public Optional<User> findById(int userId) {
        String sql = "SELECT * FROM User WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper, userId)
                .stream()
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * from User where emailId=?";
        return jdbcTemplate.query(sql, rowMapper, email).stream().findFirst();
    }
}
