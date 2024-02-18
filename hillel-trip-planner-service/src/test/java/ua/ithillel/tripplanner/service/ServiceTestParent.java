package ua.ithillel.tripplanner.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.ithillel.tripplanner.config.ServiceTestConfig;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.model.entity.HotelBooking;
import ua.ithillel.tripplanner.model.entity.HotelRoom;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.model.mapper.HotelBookingMapper;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.repo.HotelBookingRepo;
import ua.ithillel.tripplanner.repo.HotelRepo;
import ua.ithillel.tripplanner.repo.HotelRoomRepo;
import ua.ithillel.tripplanner.repo.UserRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
public class ServiceTestParent {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected HotelMapper hotelMapper;
    @Autowired
    protected HotelBookingMapper hotelBookingMapper;

    protected List<Hotel> testHotels;
    protected List<User> testUsers;
    protected List<HotelBooking> testHotelBookings;
    protected List<HotelRoom> testHotelRooms;


    @BeforeEach
    void setUpParent() {
        initHotels();
        initUsers();
        initHotelBooking();
        initHotelRooms();
    }

    private void initHotels() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotels-data.json");

        ) {

            testHotels = objectMapper.readValue(inputStream, new TypeReference<List<Hotel>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUsers() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("user-data.json");

        ) {

            testUsers = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initHotelBooking() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotel-bookings-data.json");

        ) {

            testHotelBookings = objectMapper.readValue(inputStream, new TypeReference<List<HotelBooking>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initHotelRooms() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotel-rooms-data.json");

        ) {

            testHotelRooms = objectMapper.readValue(inputStream, new TypeReference<List<HotelRoom>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
