package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
