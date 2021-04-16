package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.CompartmentDto;
import com.foodifyinc.demo.repository.CompartmentRepository;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompartmentService {

    private final CompartmentRepository compartmentRepository;
    private final FridgeRepository fridgeRepository;

    public Optional<Compartment> findById(Long id){
        return compartmentRepository.findById(id);
    }

    public boolean validateBelongsToUser(Compartment compartment, User user){
        return compartment.getFridge().getUser().equals(user);
    }

    public Compartment save(Compartment compartment){
        return compartmentRepository.save(compartment);
    }

    public Compartment update(Compartment compartment, CompartmentDto compartmentDto){
        compartment.setName(compartmentDto.getName());
        Optional<Fridge> optionalFridge = fridgeRepository.findById(compartmentDto.getFridgeId());
        optionalFridge.ifPresent(compartment::setFridge);
        return compartment;
    }


}
