package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompartmentRepository extends JpaRepository<Compartment, Long> {
}
