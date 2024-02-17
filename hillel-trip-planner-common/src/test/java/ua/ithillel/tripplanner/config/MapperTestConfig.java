package ua.ithillel.tripplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.model.mapper.HotelMapperImpl;

@Configuration
public class MapperTestConfig {
    @Bean
    public HotelMapper hotelMapper() {
        return new HotelMapperImpl();
    }
}
