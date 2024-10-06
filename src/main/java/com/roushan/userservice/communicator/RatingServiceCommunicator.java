package com.roushan.userservice.communicator;

import com.roushan.userservice.entities.Hotel;
import com.roushan.userservice.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RatingServiceCommunicator {

    private static final String RATING_SERVICE_URL = "http://RATINGSERVICE/ratings";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServiceCommunicator hotelServiceCommunicator;

    private Logger logger = Logger.getLogger(RatingServiceCommunicator.class.getName());

    public List<Rating> getRatingsByUserId(String userId) {
        String url = RATING_SERVICE_URL + "/user/"+userId;
        Rating[] ratings = this.restTemplate.getForObject(url, Rating[].class);
        List<Rating> ratingsList = Arrays.stream(ratings).toList();

        for (Rating rating : ratingsList) {
            Hotel hotel = this.hotelServiceCommunicator.getHotelByByHotelId(rating.getHotelId());
            rating.setHotel(hotel);
        }
        logger.log(Level.ALL, ratingsList.toString());
        return ratingsList;
    }


}
