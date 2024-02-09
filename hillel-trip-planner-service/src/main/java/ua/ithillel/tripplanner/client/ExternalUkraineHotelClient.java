package ua.ithillel.tripplanner.client;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.ithillel.tripplanner.model.dto.HotelDTO;

import java.util.List;

@Primary
@Component
public class ExternalUkraineHotelClient implements ExternalHotelClient {
    @Override
    public List<HotelDTO> getExternalHotels() {
        return null;
    }
}
