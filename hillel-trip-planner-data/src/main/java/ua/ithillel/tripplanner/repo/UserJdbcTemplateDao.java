package ua.ithillel.tripplanner.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.ithillel.tripplanner.jdbc.mapper.UserRowMapper;
import ua.ithillel.tripplanner.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;

@RequiredArgsConstructor
@Repository
public class UserJdbcTemplateDao implements UserRepo {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = """
                INSERT INTO t_user(name, email, birth_date) 
                VALUES(?, ?, ?)
                """;
        int affected = jdbcTemplate.update(psc -> {
            final PreparedStatement preparedStatement
                    = psc.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setObject(3, user.getBirthDate());

            return preparedStatement;
        }, keyHolder);

        if (affected == 1) {
            final Number key = keyHolder.getKey();
            if (key != null) {
                final long id = key.longValue();

                user.setId(id);

                return user;
            }
        }


        throw new RuntimeException();
    }

    @Override
    public User find(Long id) {
        String sql = "SELECT * FROM t_user WHERE id = ?";
        final User user = jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    new int[]{Types.INTEGER},
                userRowMapper);

        return user;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM t_user WHERE email = ?";
        final User user = jdbcTemplate.queryForObject(sql,
                new Object[]{email},
                new int[]{Types.VARCHAR},
                userRowMapper);

        return user;
    }

    @Override
    public User remove(User user) {
        return null;
    }
}
