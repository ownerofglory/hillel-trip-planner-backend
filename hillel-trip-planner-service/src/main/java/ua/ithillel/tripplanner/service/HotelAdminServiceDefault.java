package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
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
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) throws EntityNotFoundException, InconsistencyException {
        if (!id.equals(hotelDTO.getId())) {
            throw new InconsistencyException("Id of the hotel you're trying to updated doesn't doesn't match");
        }

        final Hotel existing = hotelRepo.find(id);
        if (existing == null) {
            throw new EntityNotFoundException("User with specified id not found: " + id);
        }

        final Hotel hotel = hotelMapper.hotelDTOToHotel(hotelDTO);
        final Hotel updatedHotel = hotelRepo.save(hotel);

        return hotelMapper.hotelToHotelDTO(updatedHotel);
    }

    @Override
    public HotelDTO deleteHotel(Long id) throws EntityNotFoundException {
        final Hotel existing = hotelRepo.find(id);
        if (existing == null) {
            throw new EntityNotFoundException("User with specified id not found: " + id);
        }

        final Hotel removed = hotelRepo.remove(existing);

        return hotelMapper.hotelToHotelDTO(removed);
    }
}
