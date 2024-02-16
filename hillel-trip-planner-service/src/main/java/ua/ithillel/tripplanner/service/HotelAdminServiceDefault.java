package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.model.mapper.HotelMapper;
import ua.ithillel.tripplanner.repo.HotelRepo;

@Service
@RequiredArgsConstructor
public class HotelAdminServiceDefault implements HotelAdminService {
    private final HotelRepo hotelRepo;
    private final HotelMapper hotelMapper;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        final Hotel hotel = hotelMapper.hotelDTOToHotel(hotelDTO);

        final Hotel saved = hotelRepo.save(hotel);

        return hotelMapper.hotelToHotelDTO(saved);
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        return null;
    }

    @Override
    public HotelDTO deleteHotel(Long id) {
        return null;
    }
}
