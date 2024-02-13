package ua.ithillel.tripplanner.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.ithillel.tripplanner.config.ServiceTestConfig;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
public class ServiceTestParent {
    @Autowired
    protected HotelMapper hotelMapper;

    @Autowired
    protected ObjectMapper objectMapper;

    protected List<Hotel> testHotels;


    @BeforeEach
    void setUpParent() {
        initHotels();
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
}
