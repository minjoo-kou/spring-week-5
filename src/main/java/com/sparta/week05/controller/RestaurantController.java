package com.sparta.week05.controller;

import com.sparta.week05.dto.RestaurantRequestDto;
import com.sparta.week05.model.Restaurant;
import com.sparta.week05.repository.RestaurantRepository;
import com.sparta.week05.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto){
        Restaurant restaurant = restaurantService.createRestaurant(requestDto);
        return restaurant;
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public Long deleteRestaurant(@PathVariable Long restaurantId){
        Long deletedId = restaurantService.deleteRestaurant(restaurantId);
        return deletedId;
    }

}
