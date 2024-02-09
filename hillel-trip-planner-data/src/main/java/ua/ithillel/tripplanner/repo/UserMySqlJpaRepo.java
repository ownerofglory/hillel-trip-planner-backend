package ua.ithillel.tripplanner.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    public User findByEmail(String email) {
        final TypedQuery<User> query =
                entityManager.createQuery("SELECT u FROM User u WHERE email = :email", User.class);

        query.setParameter("email", email);
        final User singleResult = query.getSingleResult();
        return singleResult;
    }

    @Override
    public User remove(User user) {
        try {
            entityManager.getTransaction().begin();

            entityManager.remove(user);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }
}
