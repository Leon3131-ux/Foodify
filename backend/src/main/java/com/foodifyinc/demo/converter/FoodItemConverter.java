package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.FoodItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodItemConverter {

    public FoodItem toEntity(FoodItemDto foodItemDto, User user){
        return new FoodItem(foodItemDto.getName(), user);
    }

    public FoodItemDto toDto(FoodItem foodItem){
        return new FoodItemDto(foodItem.getId(), foodItem.getName());
    }

    public List<FoodItemDto> toDtos(List<FoodItem> foodItems){
        return foodItems.stream().map(this::toDto).collect(Collectors.toList());
    }

}
