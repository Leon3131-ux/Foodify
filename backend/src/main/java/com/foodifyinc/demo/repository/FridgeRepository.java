package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Long> {

    List<Fridge> findAllByUser(User user);

}
