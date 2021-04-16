package com.foodifyinc.demo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItem extends AbstractEntity{

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    private User user;

}
