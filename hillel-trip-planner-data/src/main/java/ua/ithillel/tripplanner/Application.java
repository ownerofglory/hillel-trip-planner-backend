package ua.ithillel.tripplanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.repo.UserMySqlJpaRepo;
import ua.ithillel.tripplanner.repo.UserRepo;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        try(final EntityManagerFactory entityManagerFactory
                    = Persistence.createEntityManagerFactory("tripplannerpersistence");

            EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {


            UserRepo userRepo = new UserMySqlJpaRepo(entityManager);

            final User user = userRepo.find(2L);

            final User testUser = User.builder().email("new2@sdfds.com")
                    .birthDate(new Date())
                    .name("New2 User2")
                    .build();


            final User savedUser = userRepo.save(testUser);

            System.out.println();


        }
    }
}
