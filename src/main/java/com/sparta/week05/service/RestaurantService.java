package com.sparta.week05.service;


import com.sparta.week05.dto.RestaurantRequestDto;
import com.sparta.week05.model.Restaurant;
import com.sparta.week05.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }


    @Transactional
    public Restaurant createRestaurant(RestaurantRequestDto requestDto){
        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Long deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
        return restaurantId;
    }
}
