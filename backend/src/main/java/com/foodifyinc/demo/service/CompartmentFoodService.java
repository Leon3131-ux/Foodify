package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.SaveCompartmentFoodDto;
import com.foodifyinc.demo.repository.CompartmentFoodRepository;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompartmentFoodService {

    private final CompartmentFoodRepository compartmentFoodRepository;
    private final CompartmentRepository compartmentRepository;
    private final FoodItemRepository foodItemRepository;

    public Optional<CompartmentFood> findById(Long id){return compartmentFoodRepository.findById(id);}

    public CompartmentFood save(CompartmentFood compartmentFood){return compartmentFoodRepository.save(compartmentFood);}

    public CompartmentFood update (CompartmentFood compartmentFood, SaveCompartmentFoodDto saveCompartmentFoodDto){
        Optional<Compartment> optionalCompartment = compartmentRepository.findById(saveCompartmentFoodDto.getCompartmentId());
        Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(saveCompartmentFoodDto.getFoodItemId());
        optionalCompartment.ifPresent(compartmentFood::setCompartment);
        optionalFoodItem.ifPresent(compartmentFood::setFoodItem);
        compartmentFood.setUnit(saveCompartmentFoodDto.getUnit());
        compartmentFood.setItemAmount(saveCompartmentFoodDto.getItemAmount());
        compartmentFood.setExpirationDate(saveCompartmentFoodDto.getExpirationDate());
        return compartmentFood;
    }

    public void deleteAllByCompartment(Compartment compartment){
        List<CompartmentFood> compartmentFoods = compartmentFoodRepository.findAllByCompartment(compartment);
        compartmentFoods.forEach(compartmentFoodRepository::delete);
    }

    public void delete(CompartmentFood compartmentFood){
        compartmentFoodRepository.delete(compartmentFood);
    }

//    public List<FoodItem> findAllExpiringByUser(User user){
//
//    }

}
