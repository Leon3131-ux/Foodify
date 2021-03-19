package com.foodifyinc.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
public class Role extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName name;

    @ManyToMany()
    private Set<Permission> permissions;

}
