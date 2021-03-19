package com.foodifyinc.demo.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName name;

    @ManyToMany()
    private Set<Permission> permissions;

}
