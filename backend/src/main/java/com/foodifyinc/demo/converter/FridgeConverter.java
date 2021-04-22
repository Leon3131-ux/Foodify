package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.ReturnFridgeDto;
import com.foodifyinc.demo.dto.SaveFridgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FridgeConverter {

    private final CompartmentConverter compartmentConverter;

    public Fridge toEntity(SaveFridgeDto dto, User user){
        return new Fridge(dto.getName(), user, new ArrayList<>());
    }

    public ReturnFridgeDto toDto(Fridge fridge){
        return new ReturnFridgeDto(
                fridge.getId(),
                fridge.getName(),
                compartmentConverter.toDtos(fridge.getCompartments()));
    }

    public List<ReturnFridgeDto> toDtos(List<Fridge> fridges){
        return fridges.stream().map(this::toDto).collect(Collectors.toList());
    }

}
