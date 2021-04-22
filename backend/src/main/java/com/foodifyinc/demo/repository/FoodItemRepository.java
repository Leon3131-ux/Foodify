package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findAllByUser(User user);

}
