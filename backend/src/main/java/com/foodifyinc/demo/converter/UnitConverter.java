package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Unit;
import com.foodifyinc.demo.dto.UnitDto;
import org.springframework.stereotype.Component;

@Component
public class UnitConverter {

    public UnitDto toDto(Unit unit){
        return new UnitDto(unit.getId(), unit.getName().toString());
    }

}
