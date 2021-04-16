package com.foodifyinc.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Compartment extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    private Fridge fridge;

    @OneToMany(mappedBy = "compartment")
    private List<CompartmentFood> compartmentFoods;


}
