package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CompartmentFoodRepository extends JpaRepository<CompartmentFood, Long> {

    List<CompartmentFood> findAllByCompartment(Compartment compartment);

    @Query("SELECT f FROM CompartmentFood f WHERE f.expirationDate <= :future AND f.compartment.fridge.user = :user")
    List<CompartmentFood> findAllExpiringByUser(@Param("future") Date future, @Param("user") User user);

}
