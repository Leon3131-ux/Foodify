package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Role;
import com.foodifyinc.demo.domain.RoleName;
import com.foodifyinc.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByRoleName(RoleName roleName);
}
