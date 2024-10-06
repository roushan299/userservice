package com.roushan.userservice.communicator;

import com.roushan.userservice.entities.Hotel;
import com.roushan.userservice.entities.Rating;
import com.roushan.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HotelServiceCommunicator {

    private static final String HOTEL_SERVICE_URL = "http://HOTELSERVICE/hotel";

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = Logger.getLogger(HotelServiceCommunicator.class.getName());

    public Hotel getHotelByByHotelId(String hotelId) {
        String url = HOTEL_SERVICE_URL + "/" +hotelId;
        ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(url, Hotel.class);
        logger.log(Level.ALL, forEntity.getBody().toString());
        Hotel hotel = forEntity.getBody();
        return hotel;
    }

}
