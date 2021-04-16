package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.FridgeDto;
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
        return clazz.isAssignableFrom(FridgeDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FridgeDto fridgeDto = (FridgeDto) target;

        if(fridgeDto.getId() == null || fridgeRepository.findById(fridgeDto.getId()).isEmpty()){
            errors.rejectValue("id", "errors.fridge.id.empty");
        }
        if(fridgeDto.getName() == null || fridgeDto.getName().isBlank()){
            errors.rejectValue("name", "errors.fridge.name.empty");
        }
    }
}
