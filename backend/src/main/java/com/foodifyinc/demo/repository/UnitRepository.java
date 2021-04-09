package com.foodifyinc.demo.repository;

import com.foodifyinc.demo.domain.Unit;
import com.foodifyinc.demo.domain.UnitName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<Unit> findByName(UnitName unitName);

}
