package ua.ithillel.tripplanner.client;

import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

public interface ExternalHotelClient {
    public List<HotelDTO> getExternalHotels();
}
