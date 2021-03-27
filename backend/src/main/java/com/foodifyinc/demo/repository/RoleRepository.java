package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Role;
import com.foodifyinc.demo.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

}
