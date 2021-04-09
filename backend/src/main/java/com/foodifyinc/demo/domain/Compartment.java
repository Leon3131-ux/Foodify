package com.foodifyinc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Compartment extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    private Fridge fridge;

    @OneToMany(mappedBy = "compartment")
    private List<CompartmentFood> compartmentFoods;


}
