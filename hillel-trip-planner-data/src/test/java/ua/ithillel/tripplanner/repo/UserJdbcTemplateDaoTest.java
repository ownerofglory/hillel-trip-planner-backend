package ua.ithillel.tripplanner.repo;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.ithillel.tripplanner.model.entity.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserJdbcTemplateDaoTest extends RepoTestParent {
    private JdbcTemplate jdbcTemplate;

    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);

        userRepo = new UserJdbcTemplateDao(jdbcTemplate, userRowMapper);
    }

    @Test
    public void findTest_success() {
        Long testId = 1L;

        final User user = userRepo.find(testId);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(user.getId(), testId);
    }

    @Test
    public void findByEmailTest_success() {
        String testEmail = "vladyslavbondar@example.com";

        final User user = userRepo.findByEmail(testEmail);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertEquals(user.getEmail(), testEmail);
    }

    @Test
    public void saveTest_success() {
        final User testUser = User.builder()
                .name("TEST")
                .birthDate(new Date())
                .email("test@test.com")
                .build();

        final User savedUser = userRepo.save(testUser);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void removeTest_success() {
        final User exisiting = userRepo.find(10L);

        final User removedUser = userRepo.remove(exisiting);

        assertNotNull(removedUser);
    }
}
