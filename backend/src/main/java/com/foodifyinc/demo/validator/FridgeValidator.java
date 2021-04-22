package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.SaveFridgeDto;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class FridgeValidator implements Validator {

    private final FridgeRepository fridgeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveFridgeDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveFridgeDto saveFridgeDto = (SaveFridgeDto) target;

        if(saveFridgeDto.getId() == null){
            errors.rejectValue("id", "errors.fridge.id.empty");
        }else if(saveFridgeDto.getId() != 0 && fridgeRepository.findById(saveFridgeDto.getId()).isEmpty()){
            errors.rejectValue("id", "errors.fridge.id.empty");
        }
        if(saveFridgeDto.getName() == null || saveFridgeDto.getName().isBlank()){
            errors.rejectValue("name", "errors.fridge.name.empty");
        }
    }
}
