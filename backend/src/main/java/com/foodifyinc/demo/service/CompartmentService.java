package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.SaveCompartmentDto;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompartmentService {

    private final CompartmentRepository compartmentRepository;
    private final FridgeRepository fridgeRepository;
    private final CompartmentFoodService compartmentFoodService;

    public Optional<Compartment> findById(Long id){
        return compartmentRepository.findById(id);
    }

    public boolean validateBelongsToUser(Compartment compartment, User user){
        return compartment.getFridge().getUser().equals(user);
    }

    public Compartment save(Compartment compartment){
        return compartmentRepository.save(compartment);
    }

    public Compartment update(Compartment compartment, SaveCompartmentDto saveCompartmentDto){
        compartment.setName(saveCompartmentDto.getName());
        Optional<Fridge> optionalFridge = fridgeRepository.findById(saveCompartmentDto.getFridgeId());
        optionalFridge.ifPresent(compartment::setFridge);
        return compartment;
    }

    public void deleteAllByFridge(Fridge fridge){
        List<Compartment> compartments = compartmentRepository.findAllByFridge(fridge);
        compartments.forEach(compartmentFoodService::deleteAllByCompartment);
        compartmentRepository.deleteInBatch(compartments);
    }

    public void delete(Compartment compartment){
        compartmentFoodService.deleteAllByCompartment(compartment);
        compartmentRepository.delete(compartment);
    }


}
