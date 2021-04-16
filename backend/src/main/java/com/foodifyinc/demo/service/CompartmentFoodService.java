package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.repository.CompartmentFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompartmentFoodService {

    private final CompartmentFoodRepository compartmentFoodRepository;

    public CompartmentFood save(CompartmentFood compartmentFood){return compartmentFoodRepository.save(compartmentFood);}

}
