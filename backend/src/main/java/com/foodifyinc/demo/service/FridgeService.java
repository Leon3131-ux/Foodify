package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.SaveFridgeDto;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FridgeService {

    private final FridgeRepository fridgeRepository;

    public Optional<Fridge> findById(Long id){return fridgeRepository.findById(id);}

    public boolean fridgeBelongsToUser(Fridge fridge, User user){
        return fridge.getUser().equals(user);
    }

    public Fridge update(Fridge fridge, SaveFridgeDto saveFridgeDto){
        fridge.setName(saveFridgeDto.getName());
        return fridge;
    }

    public Fridge save(Fridge fridge){
        return fridgeRepository.save(fridge);
    }

    public List<Fridge> findByUser(User user){
        return fridgeRepository.findAllByUser(user);
    }

}
