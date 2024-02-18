package ua.ithillel.tripplanner.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.service.HotelService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class HotelControllerTest extends ControllerTestParent {
    @Mock
    private HotelService hotelServiceMock;

    private HotelController hotelController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelController = new HotelController(hotelServiceMock);

        this.mockMvc = MockMvcBuilders.standaloneSetup(hotelController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    public void getAllHotelsTest_success() throws Exception {
        when(hotelServiceMock.getAllHotels()).thenReturn(testHotelItems);

        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllHotelById_success() throws Exception {
        when(hotelServiceMock.getHotelById(anyLong())).thenReturn(testHotels.get(0));

        Long testId = 100L;

        mockMvc.perform(get("/hotels/" + testId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllHotelById_returnsNotFound() throws Exception {
        when(hotelServiceMock.getHotelById(anyLong())).thenThrow(new EntityNotFoundException(""));

        Long testId = 9999999L;

        mockMvc.perform(get("/hotels/" + testId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void searchHotels_success() throws Exception {
        when(hotelServiceMock.searchHotels(anyInt(), anyInt())).thenReturn(testHotelItems);

        int testLimit = 100;
        int testPage = 1;

        mockMvc.perform(get("/hotels/search")
                        .queryParam("limit", "" + testLimit)
                        .queryParam("page", "" + testPage)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
