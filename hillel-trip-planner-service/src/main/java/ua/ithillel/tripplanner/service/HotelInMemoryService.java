package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.client.ExternalHotelClient;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;

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

    private List<HotelListItemDTO> hotels = new ArrayList<>();

    @Override
    public List<HotelListItemDTO> getAllHotels() {
        return hotels;
    }

    @Override
    public List<HotelListItemDTO> searchHotels(int limit, int page) {
        return null;
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        return null;
    }
}
