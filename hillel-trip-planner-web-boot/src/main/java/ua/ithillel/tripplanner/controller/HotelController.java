package ua.ithillel.tripplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.service.HotelSearchService;
import ua.ithillel.tripplanner.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final HotelSearchService hotelSearchService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<HotelListItemDTO>> getAllHotels() {
        final List<HotelListItemDTO> allHotels = hotelService.getAllHotels();

        return ResponseEntity.ok(allHotels);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") long id) throws EntityNotFoundException {
        final HotelDTO hotelById = hotelService.getHotelById(id);

        return ResponseEntity.ok(hotelById);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<HotelListItemDTO>> searchHotels(@RequestParam("limit") int limit,
                                                       @RequestParam("page") int page) {

//        final List<HotelListItemDTO> hotels = hotelService.searchHotels(limit, page);

        final List<HotelListItemDTO> hotels = hotelSearchService.searchHotels(limit, page);

        return ResponseEntity.ok(hotels);
    }

}
