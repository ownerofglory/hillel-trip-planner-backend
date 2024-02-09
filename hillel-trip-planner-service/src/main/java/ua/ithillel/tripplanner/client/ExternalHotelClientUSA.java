package ua.ithillel.tripplanner.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

@Component
@Qualifier("usaClient")
public class ExternalHotelClientUSA implements ExternalHotelClient {
    @Override
    public List<HotelDTO> getExternalHotels() {
        return null;
    }
}
