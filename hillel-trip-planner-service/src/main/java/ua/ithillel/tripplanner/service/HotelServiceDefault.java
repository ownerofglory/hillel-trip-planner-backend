package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.repo.HotelRepo;

import java.util.List;


@RequiredArgsConstructor
@Service
@Primary
public class HotelServiceDefault implements HotelService {
    private final HotelRepo hotelRepo;
    private final HotelMapper hotelMapper;


    @Override
    public List<HotelDTO> getAllHotels() {
        final List<Hotel> hotels = hotelRepo.findAll();

        return hotels.stream().map(hotelMapper::hotelToHotelDTO).toList();
    }
}
