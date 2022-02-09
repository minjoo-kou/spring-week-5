package com.sparta.week05.service;

import com.sparta.week05.dto.OrderItemRequestDto;
import com.sparta.week05.dto.OrderRequestDto;
import com.sparta.week05.dto.OrderResponseDto;
import com.sparta.week05.dto.OrderResponseFoodsDto;
import com.sparta.week05.model.Food;
import com.sparta.week05.model.Orders;
import com.sparta.week05.model.OrderItem;
import com.sparta.week05.model.Restaurant;
import com.sparta.week05.repository.FoodRepository;
import com.sparta.week05.repository.OrderItemRepository;
import com.sparta.week05.repository.OrderRepository;
import com.sparta.week05.repository.RestaurantRepository;
import com.sparta.week05.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderService{
    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    };

    @Transactional
    public OrderResponseDto createOrders(OrderRequestDto requestDto) {
        //레스토랑 ID로 주문 음식점 불러오기 id, name, minOrderPrice, deliveryFee

        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElse(null);

        String restaurantName = restaurant.getName();
        int minOrderPrice = restaurant.getMinOrderPrice();
        int deliveryFee = restaurant.getDeliveryFee();

        Orders orders = new Orders(restaurant);
        orderRepository.save(orders);

        List<OrderResponseFoodsDto> foods = new ArrayList<>();
        int totalPrice = 0;
        for(OrderItemRequestDto eachItem : requestDto.getFoods()){
            Food food = foodRepository.findById(eachItem.getId())
                    .orElse(null);

            int quantity = eachItem.getQuantity();
            int price = food.getPrice() * quantity;

            OrderItem orderItem = new OrderItem(orders, food, quantity, price);
            orderItemRepository.save(orderItem);

            OrderResponseFoodsDto eachFoodResponse = new OrderResponseFoodsDto(food.getName(), quantity, price);
            foods.add(eachFoodResponse);
            totalPrice += price;
        }

        OrderValidator.validateOrderPrice(minOrderPrice, totalPrice);

        totalPrice = totalPrice + deliveryFee;

        OrderResponseDto orderResponse = new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);

        return orderResponse;

    }

    public List<OrderResponseDto> getOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderResponseDto> orderLists = new ArrayList<>();

        for(Orders eachOrder : orders){
            String restaurantName = eachOrder.getRestaurant().getName();
            int deliveryFee = eachOrder.getRestaurant().getDeliveryFee();

            List<OrderResponseFoodsDto> foods = new ArrayList<>();
            List<OrderItem> foodsTemp = orderItemRepository.findAllByOrdersId(eachOrder.getId());
            int totalPrice = 0;
            for(OrderItem eachItem : foodsTemp){
                String name = eachItem.getFood().getName();
                int quantity = eachItem.getQuantity();
                int price = eachItem.getPrice();

                OrderResponseFoodsDto eachItems = new OrderResponseFoodsDto(name, quantity, price);
                foods.add(eachItems);
                totalPrice += price;
            }
            totalPrice = totalPrice + deliveryFee;
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);
            orderLists.add(orderResponseDto);
        }
        return orderLists;
    }
}
