package ua.ithillel.tripplanner.repo;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.query.Query;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

@RequiredArgsConstructor
public class HotelHibernateSessionRepo implements HotelRepo {
    private final Session session;

    @Override
    public Hotel save(Hotel hotel) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.persist(hotel);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return hotel;
    }

    @Override
    public Hotel find(Long id) {
        return session.find(Hotel.class, id);
    }

    @Override
    public List<Hotel> findAll() {
        final Query<Hotel> query = session.createQuery("SELECT h FROM Hotel h", Hotel.class);
        return query.getResultList();
    }

    @Override
    public Hotel remove(Hotel hotel) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.remove(hotel);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return hotel;
    }
}