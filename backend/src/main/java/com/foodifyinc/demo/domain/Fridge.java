package com.foodifyinc.demo.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Fridge extends AbstractEntity {

    @Column(nullable = false)
    private String Name;



}
