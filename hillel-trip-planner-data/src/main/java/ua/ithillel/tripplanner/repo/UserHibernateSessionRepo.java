package ua.ithillel.tripplanner.repo;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.ithillel.tripplanner.model.entity.User;

@RequiredArgsConstructor
public class UserHibernateSessionRepo implements UserRepo {
    private final Session session;

    @Override
    public User save(User user) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.persist(user);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return user;
    }

    @Override
    public User find(Long id) {
        return session.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        final TypedQuery<User> query =
                session.createQuery("SELECT u FROM User u WHERE email = :email", User.class);

        query.setParameter("email", email);
        final User singleResult = query.getSingleResult();
        return singleResult;
    }

    @Override
    public User remove(User user) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.remove(user);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return user;
    }
}
