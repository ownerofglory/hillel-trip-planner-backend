package ua.ithillel.tripplanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.service.HotelService;

import java.util.List;

public class Application {
    public static void main(String[] args) {
//        HotelService hotelService = new HotelInMemoryService();
//        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext annoContext
                = new AnnotationConfigApplicationContext("ua.ithillel.tripplanner");

//        final HotelService hotelService = (HotelService) xmlContext.getBean("hotelService");
        final HotelService hotelService1 = annoContext.getBean(HotelService.class);
//        final HotelService hotelService2 = annoContext.getBean(HotelService.class);

        final List<HotelListItemDTO> allHotels = hotelService1.getAllHotels();

        System.out.println();
    }
}
