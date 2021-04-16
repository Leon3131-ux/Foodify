package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
