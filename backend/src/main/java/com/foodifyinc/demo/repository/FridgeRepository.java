package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Long> {



}
