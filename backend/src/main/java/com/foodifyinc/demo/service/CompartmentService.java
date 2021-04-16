package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.repository.CompartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompartmentService {

    private final CompartmentRepository compartmentRepository;

    public Compartment save(Compartment compartment){
        return compartmentRepository.save(compartment);
    }


}
