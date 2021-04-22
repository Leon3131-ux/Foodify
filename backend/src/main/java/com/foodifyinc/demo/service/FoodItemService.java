package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.FoodItemDto;
import com.foodifyinc.demo.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public boolean validateBelongsToUser(FoodItem foodItem, User user){
        return foodItem.getUser().equals(user);
    }

    public Optional<FoodItem> findById(Long id){
        return foodItemRepository.findById(id);
    }

    public List<FoodItem> findByUser(User user){
        return foodItemRepository.findAllByUser(user);
    }

    public FoodItem update(FoodItem foodItem, FoodItemDto foodItemDto){
        foodItem.setName(foodItemDto.getName());
        return foodItem;
    }

    public FoodItem save(FoodItem foodItem){return foodItemRepository.save(foodItem);}

    public void delete(FoodItem foodItem){
        foodItemRepository.delete(foodItem);
    }

}
