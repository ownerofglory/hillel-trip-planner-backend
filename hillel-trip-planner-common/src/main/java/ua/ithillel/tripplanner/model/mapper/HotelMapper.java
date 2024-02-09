package ua.ithillel.tripplanner.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    Hotel hotelDTOToHotel(HotelDTO dto);
    HotelDTO hotelToHotelDTO(Hotel hotel);
    HotelListItemDTO hotelToHotelListViewDTO(Hotel hotel);

}
