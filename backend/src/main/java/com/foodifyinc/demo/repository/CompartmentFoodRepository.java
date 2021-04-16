package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.CompartmentFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompartmentFoodRepository extends JpaRepository<CompartmentFood, Long> {
}
