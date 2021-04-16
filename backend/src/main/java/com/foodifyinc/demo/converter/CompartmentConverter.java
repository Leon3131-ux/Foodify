package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.dto.CompartmentDto;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompartmentConverter {

    private final FridgeRepository fridgeRepository;

    public Compartment toEntity(CompartmentDto dto){
        Optional<Fridge> optionalFridge = fridgeRepository.findById(dto.getFridgeId());
        return optionalFridge.map(fridge -> new Compartment(dto.getName(), fridge, new ArrayList<>())).orElse(null);
    }

    public CompartmentDto toDto(Compartment compartment){
        return new CompartmentDto(compartment.getName(), compartment.getFridge().getId());
    }

}
