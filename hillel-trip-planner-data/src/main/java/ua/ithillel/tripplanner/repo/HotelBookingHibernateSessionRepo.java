package ua.ithillel.tripplanner.repo;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ua.ithillel.tripplanner.model.entity.HotelBooking;

@RequiredArgsConstructor
@Repository
public class HotelBookingHibernateSessionRepo implements HotelBookingRepo {
    private final Session session;

    @Override
    public HotelBooking save(HotelBooking hotelBooking) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.persist(hotelBooking);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return hotelBooking;
    }

    @Override
    public HotelBooking findById(Long id) {
        return session.find(HotelBooking.class, id);
    }

    @Override
    public HotelBooking remove(HotelBooking hotelBooking) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.remove(hotelBooking);
            session.flush();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return hotelBooking;
    }
}
