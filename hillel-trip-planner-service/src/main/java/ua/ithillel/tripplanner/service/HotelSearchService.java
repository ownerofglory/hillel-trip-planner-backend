package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;

import java.util.List;

public interface HotelSearchService {
    List<HotelListItemDTO> searchHotels(int limit, int page);
}
