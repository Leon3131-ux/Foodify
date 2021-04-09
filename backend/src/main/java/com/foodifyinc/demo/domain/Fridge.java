package com.foodifyinc.demo.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Fridge extends AbstractEntity {

    @Column(nullable = false)
    private String Name;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "fridge")
    private List<Compartment> compartments;

}
