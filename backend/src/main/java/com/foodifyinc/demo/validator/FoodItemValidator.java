package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.FoodItemDto;
import com.foodifyinc.demo.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class FoodItemValidator implements Validator {

    private final FoodItemRepository foodItemRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FoodItemDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FoodItemDto foodItemDto = (FoodItemDto) target;

        if(foodItemDto.getId() == null){
            errors.rejectValue("id", "errors.foodItem.id.empty");
        }else if(foodItemDto.getId() !=0 && foodItemRepository.findById(foodItemDto.getId()).isEmpty()){
            errors.rejectValue("id", "errors.foodItem.id.empty");
        }

        if(foodItemDto.getName() == null || foodItemDto.getName().isBlank()){
            errors.rejectValue("name", "errors.foodItem.name.empty");
        }
    }
}
