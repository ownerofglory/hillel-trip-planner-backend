package ua.ithillel.tripplanner.repo;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import ua.ithillel.tripplanner.model.entity.User;

@RequiredArgsConstructor
public class UserMySqlJpaRepo implements UserRepo {
    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User find(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User remove(Long id) {

        return null;
    }
}
