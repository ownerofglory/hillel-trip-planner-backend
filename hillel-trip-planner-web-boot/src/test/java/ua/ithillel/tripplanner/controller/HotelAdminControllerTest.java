package ua.ithillel.tripplanner.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.service.HotelAdminService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HotelAdminControllerTest extends ControllerTestParent {
    @Mock
    private HotelAdminService hotelAdminServiceMock;
    private HotelAdminController hotelAdminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelAdminController = new HotelAdminController(hotelAdminServiceMock);

        this.mockMvc = MockMvcBuilders.standaloneSetup(hotelAdminController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    public void createHotelTest_success() throws Exception {
        when(hotelAdminServiceMock.createHotel(any())).thenReturn(testHotels.get(0));

        final HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName("test");
        hotelDTO.setDescription("test");
        hotelDTO.setPhoneNumber("+10000000");

        mockMvc.perform(post("/hotel-admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hotelDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}
