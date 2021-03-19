package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
