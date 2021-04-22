package com.foodifyinc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnCompartmentDto {

    private Long id;
    private String name;
    private Long fridgeId;
    private List<ReturnCompartmentFoodDto> compartmentFoodDtos;

}
