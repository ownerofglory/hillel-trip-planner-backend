package ua.ithillel.tripplanner.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.ithillel.tripplanner.config.HibernateTestConfig;
import ua.ithillel.tripplanner.model.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserHibernateSessionRepoTest extends RepoTestParent {
    private UserRepo userRepo;
    private Session testSession;

    @BeforeEach
    public void setUp() {
        testSession = testSessionFactory.openSession();

        userRepo = new UserHibernateSessionRepo(testSession);
    }
    @Test
    public void saveTest_userSaved() {
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
    public void findTest_returnsNonNullUserById() {
        Long testId = 1L;
        final User user = userRepo.find(testId);

        assertNotNull(user);
        assertEquals(user.getId(), testId);
    }

    @Test
    public void findByEmailTest_returnsNonNullUserByEmail() {
        String testEmail = "oleksandrkovalenko@example.com";
        final User user = userRepo.findByEmail(testEmail);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(user.getEmail(), testEmail);
    }

    @Test
    public void removeTest_returnsNonNullForExistingUser() {
        final User exisiting = userRepo.find(10L);

        final User removedUser = userRepo.remove(exisiting);

        assertNotNull(removedUser);
    }

    @AfterEach
    public void tearDown() {
        testSession.close();
//        testSessionFactory.close();
    }

}
