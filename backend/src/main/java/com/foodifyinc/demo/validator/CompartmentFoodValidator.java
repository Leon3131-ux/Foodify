package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.SaveCompartmentFoodDto;
import com.foodifyinc.demo.repository.CompartmentFoodRepository;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
@RequiredArgsConstructor

public class CompartmentFoodValidator implements Validator {

    private final CompartmentRepository compartmentRepository;
    private final CompartmentFoodRepository compartmentFoodRepository;
    private final FoodItemRepository foodItemRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveCompartmentFoodDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveCompartmentFoodDto dto = (SaveCompartmentFoodDto) target;

        if(dto.getId() == null){
            errors.rejectValue("id", "errors.foodItem.id.empty");
        }else if(dto.getId() != 0 && compartmentFoodRepository.findById(dto.getId()).isEmpty()){
            errors.rejectValue("id", "errors.foodItem.id.empty");
        }

        if(compartmentRepository.findById(dto.getCompartmentId()).isEmpty()){
            errors.rejectValue("compartmentId", "errors.foodItem.compartmentId.empty");
        }

        if(foodItemRepository.findById(dto.getFoodItemId()).isEmpty()){
            errors.rejectValue("foodItemId", "errors.foodItem.foodItemId.empty");
        }

        if(dto.getItemAmount() == null || dto.getItemAmount() < 1){
            errors.rejectValue("itemAmount", "errors.foodItem.itemAmount.empty");
        }

        if(dto.getUnit() == null || dto.getUnit().isBlank()){
            errors.rejectValue("unit", "errors.foodItem.unit.empty");
        }

        if(dto.getExpirationDate() == null || dto.getExpirationDate().before(new Date())){
            errors.rejectValue("expirationDate", "errors.foodItem.expirationDate.empty");
        }

    }
}
