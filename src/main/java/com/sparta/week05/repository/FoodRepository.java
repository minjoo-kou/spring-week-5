package com.sparta.week05.repository;

import com.sparta.week05.model.Food;
import com.sparta.week05.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
//    List<FoodMapping> findAllByRestaurant(Restaurant restaurant);


//    @Query("SELECT id, name, price FROM Food")
//    List<Object> findAllByRestaurant(Restaurant restaurant);
}
