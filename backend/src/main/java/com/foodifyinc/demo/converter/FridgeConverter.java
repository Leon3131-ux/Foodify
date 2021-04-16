package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.FridgeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class FridgeConverter {

    public Fridge toEntity(FridgeDto dto, User user){
        return new Fridge(dto.getName(), user, new ArrayList<>());
    }

    public FridgeDto toDto(Fridge fridge){
        return new FridgeDto(fridge.getId(), fridge.getName());
    }

}
