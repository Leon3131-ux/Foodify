package com.foodifyinc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnCompartmentFoodDto {

    private Long id;
    private FoodItemDto foodItemDto;
    private Long itemAmount;
    private Double unitAmount;
    private UnitDto unitDto;
    private Long compartmentId;
    private Date expirationDate;

}
