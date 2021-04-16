package com.foodifyinc.demo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Unit extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitName name;

}
