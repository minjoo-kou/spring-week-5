package com.sparta.week05.model;

import com.sparta.week05.dto.FoodRequestDto;
import com.sparta.week05.validator.FoodValidator;
import com.sparta.week05.validator.RestaurantValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Food(String name, int price, Restaurant restaurant){
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;

    }

}
