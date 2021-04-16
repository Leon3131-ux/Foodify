package com.foodifyinc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
public class Permission extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PermissionName name;

}
