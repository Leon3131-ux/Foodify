package com.foodifyinc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveCompartmentDto {

    private Long id;
    private String name;
    private Long fridgeId;

}
