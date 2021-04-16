package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FridgeService {

    private final FridgeRepository fridgeRepository;

    public boolean fridgeIsPresentAndBelongsToUser(Long fridgeId, User user){
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        return optionalFridge.map(fridge -> fridge.getUser().equals(user)).orElse(false);
    }

}
