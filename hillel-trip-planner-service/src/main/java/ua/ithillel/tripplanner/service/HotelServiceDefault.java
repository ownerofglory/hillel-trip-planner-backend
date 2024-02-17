package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
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
    public  List<HotelListItemDTO> getAllHotels() {
        final List<Hotel> hotels = hotelRepo.findAll();

        return hotels.stream().map(hotelMapper::hotelToHotelListViewDTO).toList();
    }

    @Override
    public List<HotelListItemDTO> searchHotels(int limit, int page) {
        // consideration of pagination is to be done in further steps
        final List<Hotel> hotels = hotelRepo.findAll();

        return hotels.stream().map(hotelMapper::hotelToHotelListViewDTO).toList();
    }

    @Override
    public HotelDTO getHotelById(Long id) throws EntityNotFoundException {
        final Hotel hotel = hotelRepo.find(id);

        if (hotel == null) {
            throw new EntityNotFoundException("No hotel with given id: " + id);
        }

        return hotelMapper.hotelToHotelDTO(hotel);
    }
}
