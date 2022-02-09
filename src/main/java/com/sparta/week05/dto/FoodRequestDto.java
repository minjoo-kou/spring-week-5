package com.sparta.week05.dto;

import com.sparta.week05.model.Food;
import com.sparta.week05.validator.RestaurantValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class FoodRequestDto {

    private final String name;
    private final int price;

//    private List<FoodRequestDto> foodRequestDtoList;

}



