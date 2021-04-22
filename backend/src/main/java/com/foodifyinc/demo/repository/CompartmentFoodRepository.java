package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompartmentFoodRepository extends JpaRepository<CompartmentFood, Long> {

    List<CompartmentFood> findAllByCompartment(Compartment compartment);

}
