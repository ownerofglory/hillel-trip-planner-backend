package ua.ithillel.tripplanner.model.mapper;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.ithillel.tripplanner.config.MapperTestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MapperTestConfig.class})
public class MapperTestParent {
    @Autowired
    protected HotelMapper hotelMapper;
}
