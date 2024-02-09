package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.client.ExternalHotelClient;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "prototype")
@RequiredArgsConstructor
public class HotelInMemoryService implements HotelService {
    @Qualifier("usaClient")
    private final ExternalHotelClient externalHotelClient;

//    public HotelInMemoryService(ExternalHotelClient externalHotelClient) {
//        this.externalHotelClient = externalHotelClient;
//    }

    private List<HotelDTO> hotels = new ArrayList<>();

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotels;
    }
}
