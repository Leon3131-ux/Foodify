package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.CompartmentDto;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CompartmentValidator implements Validator {

    private final FridgeRepository fridgeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CompartmentDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompartmentDto dto = (CompartmentDto) target;

        if(dto.getName() == null || dto.getName().isBlank()){
            errors.rejectValue("name", "errors.compartment.name.empty");
        }

        if(dto.getFridgeId() == null){
            errors.rejectValue("fridgeId", "errors.compartment.fridgeId.empty");
        }else if(fridgeRepository.findById(dto.getFridgeId()).isEmpty()){
            errors.rejectValue("fridgeId", "errors.compartment.fridgeId.empty");
        }

    }
}
