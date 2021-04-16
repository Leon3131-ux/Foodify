package com.foodifyinc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SaveCompartmentFoodDto {

    private Long CompartmentId;
    private String name;
    private Long itemAmount;
    private Double unitAmount;
    private Long unitId;
    private Date expirationDate;

}
