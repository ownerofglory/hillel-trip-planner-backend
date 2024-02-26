package ua.ithillel.tripplanner.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ua.ithillel.tripplanner.config.ControllerTestConfig;
import ua.ithillel.tripplanner.exception.GlobalExceptionHandler;
import ua.ithillel.tripplanner.model.dto.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ControllerTestConfig.class})
public class ControllerTestParent {
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected GlobalExceptionHandler globalExceptionHandler;
    protected MockMvc mockMvc;
    protected List<HotelDTO> testHotels;
    protected List<HotelListItemDTO> testHotelItems;
    protected List<UserDTO> testUsers;
    protected List<HotelBookingDTO> testHotelBookings;
    protected List<HotelRoomDTO> testHotelRooms;


    @BeforeEach
    void setUpParent() {
        initHotels();
        initHotelItems();
        initUsers();
        initHotelBooking();
        initHotelRooms();
    }

    private void initHotels() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotels-data.json");

        ) {

            testHotels = objectMapper.readValue(inputStream, new TypeReference<List<HotelDTO>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initHotelItems() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotels-data.json");

        ) {

            testHotelItems = objectMapper.readValue(inputStream, new TypeReference<List<HotelListItemDTO>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUsers() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("user-data.json");

        ) {

            testUsers = objectMapper.readValue(inputStream, new TypeReference<List<UserDTO>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initHotelBooking() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotel-bookings-data.json");

        ) {

            testHotelBookings = objectMapper.readValue(inputStream, new TypeReference<List<HotelBookingDTO>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initHotelRooms() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("hotel-rooms-data.json");

        ) {

            testHotelRooms = objectMapper.readValue(inputStream, new TypeReference<List<HotelRoomDTO>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
