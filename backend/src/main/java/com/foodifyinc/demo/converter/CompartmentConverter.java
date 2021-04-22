package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.dto.ReturnCompartmentDto;
import com.foodifyinc.demo.dto.SaveCompartmentDto;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompartmentConverter {

    private final FridgeRepository fridgeRepository;
    private final CompartmentFoodConverter compartmentFoodConverter;

    public Compartment toEntity(SaveCompartmentDto dto){
        Optional<Fridge> optionalFridge = fridgeRepository.findById(dto.getFridgeId());
        return optionalFridge.map(fridge -> new Compartment(dto.getName(), fridge, new ArrayList<>())).orElse(null);
    }

    public ReturnCompartmentDto toDto(Compartment compartment){
        return new ReturnCompartmentDto(
                compartment.getId(),
                compartment.getName(),
                compartment.getFridge().getId(),
                compartmentFoodConverter.toDtos(compartment.getCompartmentFoods()));
    }

    public List<ReturnCompartmentDto> toDtos(List<Compartment> compartments){
        return compartments.stream().map(this::toDto).collect(Collectors.toList());
    }

}
