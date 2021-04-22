package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.dto.ReturnCompartmentFoodDto;
import com.foodifyinc.demo.dto.SaveCompartmentFoodDto;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompartmentFoodConverter {

    private final FoodItemRepository foodItemRepository;
    private final CompartmentRepository compartmentRepository;
    private final FoodItemConverter foodItemConverter;

    public CompartmentFood toEntity(SaveCompartmentFoodDto dto){
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(dto.getFoodItemId());
        Optional<Compartment> optionalCompartment = compartmentRepository.findById(dto.getCompartmentId());
        if(optionalFoodItem.isPresent() && optionalCompartment.isPresent()){
            return new CompartmentFood(
                    optionalFoodItem.get(),
                    optionalCompartment.get(),
                    dto.getItemAmount(),
                    dto.getUnit(),
                    dto.getExpirationDate());
        }
        return null;
    }

    public ReturnCompartmentFoodDto toDto(CompartmentFood compartmentFood){
        return new ReturnCompartmentFoodDto(
                compartmentFood.getId(),
                foodItemConverter.toDto(compartmentFood.getFoodItem()),
                compartmentFood.getItemAmount(),
                compartmentFood.getUnit(),
                compartmentFood.getCompartment().getId(),
                compartmentFood.getExpirationDate());
    }

    public List<ReturnCompartmentFoodDto> toDtos(List<CompartmentFood> compartmentFoods){
        return compartmentFoods.stream().map(this::toDto).collect(Collectors.toList());
    }

}
