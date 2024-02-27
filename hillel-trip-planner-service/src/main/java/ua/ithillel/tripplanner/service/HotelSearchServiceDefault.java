package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.repo.HotelJpaRepo;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelSearchServiceDefault implements HotelSearchService {
    private final HotelJpaRepo hotelJpaRepo;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelListItemDTO> searchHotels(int limit, int page) {
        final PageRequest pageRequest = PageRequest.of(page, limit);
        final Page<Hotel> hotelPage = hotelJpaRepo.findAll(pageRequest);

        final List<HotelListItemDTO> hotelList
                = hotelPage.stream().map(hotelMapper::hotelToHotelListViewDTO).toList();

        return hotelList;
    }
}
