package com.foodifyinc.demo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompartmentFood extends AbstractEntity{

    @ManyToOne()
    private FoodItem foodItem;

    @ManyToOne()
    private Compartment compartment;

    @Column(nullable = false)
    private Long itemAmount;

    @Column(nullable = false, precision = 2)
    private Double unitAmount;

    @ManyToOne
    private Unit unit;

    @Column(nullable = false)
    private Date expirationDate;

}
