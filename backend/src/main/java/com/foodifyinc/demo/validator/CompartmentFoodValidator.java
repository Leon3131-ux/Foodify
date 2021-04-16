package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.SaveCompartmentFoodDto;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FoodItemRepository;
import com.foodifyinc.demo.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
@RequiredArgsConstructor

public class CompartmentFoodValidator implements Validator {

    private final CompartmentRepository compartmentRepository;
    private final FoodItemRepository foodItemRepository;
    private final UnitRepository unitRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveCompartmentFoodDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveCompartmentFoodDto dto = (SaveCompartmentFoodDto) target;

        if(compartmentRepository.findById(dto.getCompartmentId()).isEmpty()){
            errors.rejectValue("compartmentId", "errors.foodItem.compartmentId.empty");
        }

        if(foodItemRepository.findById(dto.getFoodItemId()).isEmpty()){
            errors.rejectValue("foodItemId", "errors.foodItem.foodItemId.empty");
        }

        if(dto.getItemAmount() == null || dto.getItemAmount() < 1){
            errors.rejectValue("itemAmount", "errors.foodItem.itemAmount.empty");
        }

        if(dto.getUnitAmount() == null || dto.getUnitAmount() < 0.01){
            errors.rejectValue("unitAmount", "errors.foodItem.unitAmount.empty");
        }

        if(unitRepository.findById(dto.getUnitId()).isEmpty()){
            errors.rejectValue("unitId", "errors.foodItem.unitId.empty");
        }

        if(dto.getExpirationDate() == null || dto.getExpirationDate().before(new Date())){
            errors.rejectValue("expirationDate", "errors.foodItem.expirationDate.empty");
        }

    }
}
