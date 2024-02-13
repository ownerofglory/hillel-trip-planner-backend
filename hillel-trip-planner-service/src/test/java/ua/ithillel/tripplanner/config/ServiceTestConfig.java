package ua.ithillel.tripplanner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.model.mapper.HotelMapperImpl;

@Configuration
public class ServiceTestConfig {
    @Bean
    public HotelMapper hotelMapper() {
        return new HotelMapperImpl();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
