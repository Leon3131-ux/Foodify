package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.repository.CompartmentFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompartmentFoodService {

    private final CompartmentFoodRepository compartmentFoodRepository;

    public CompartmentFood save(CompartmentFood compartmentFood){return compartmentFoodRepository.save(compartmentFood);}

    public void deleteAllByCompartment(Compartment compartment){
        List<CompartmentFood> compartmentFoods = compartmentFoodRepository.findAllByCompartment(compartment);
        compartmentFoods.forEach(compartmentFoodRepository::delete);
    }

    public void delete(CompartmentFood compartmentFood){
        compartmentFoodRepository.delete(compartmentFood);
    }

}
