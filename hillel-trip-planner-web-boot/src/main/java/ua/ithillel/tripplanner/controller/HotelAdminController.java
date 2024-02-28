package ua.ithillel.tripplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.service.HotelAdminService;

@RestController
@RequestMapping("/hotel-admin")
@RequiredArgsConstructor
public class HotelAdminController {
    private final HotelAdminService hotelAdminService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        final HotelDTO hotel = hotelAdminService.createHotel(hotelDTO);

        return ResponseEntity.ok(hotel);
    }
}
