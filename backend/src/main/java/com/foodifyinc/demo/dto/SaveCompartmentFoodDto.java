
package com.foodifyinc.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveCompartmentFoodDto {

    private Long compartmentId;
    private Long foodItemId;
    private Long itemAmount;
    private Double unitAmount;
    private Long unitId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

}
