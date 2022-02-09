package com.sparta.week05.controller;


import com.sparta.week05.dto.FoodRequestDto;
import com.sparta.week05.dto.RestaurantRequestDto;
import com.sparta.week05.model.Food;
import com.sparta.week05.service.FoodService;
import com.sparta.week05.validator.FoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class FoodController {
    private final FoodService foodservice;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        List<Food> foods = foodservice.getFoods(restaurantId);
        return foods;
    }

    @PostMapping("restaurant/{restaurantId}/food/register")
    public void createFoods(@RequestBody List<FoodRequestDto> requestDtoList, @PathVariable Long restaurantId){
        FoodValidator.validateFoodInput(requestDtoList);
        foodservice.createFoods(requestDtoList, restaurantId);
    }
}
