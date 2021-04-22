package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.SaveCompartmentDto;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CompartmentValidator implements Validator {

    private final FridgeRepository fridgeRepository;
    private final CompartmentRepository compartmentRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveCompartmentDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveCompartmentDto dto = (SaveCompartmentDto) target;

        if(dto.getId() == null){
            errors.rejectValue("id", "errors.compartment.id.empty");
        }else if(dto.getId() != 0 && compartmentRepository.findById(dto.getId()).isEmpty()){
            errors.rejectValue("id", "errors.compartment.id.empty");
        }

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
