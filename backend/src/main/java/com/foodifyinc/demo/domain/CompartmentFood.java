package com.foodifyinc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompartmentFood extends AbstractEntity{

    @ManyToOne()
    private FoodItem foodItem;

    @ManyToOne
    private Compartment compartment;

    @Column(nullable = false, precision = 2)
    private Double amount;

    @Column(nullable = false)
    private Date expirationDate;

}
