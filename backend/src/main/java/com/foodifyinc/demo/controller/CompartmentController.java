package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.CompartmentConverter;
import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.CompartmentDto;
import com.foodifyinc.demo.service.CompartmentService;
import com.foodifyinc.demo.service.FridgeService;
import com.foodifyinc.demo.service.UserService;
import com.foodifyinc.demo.validator.CompartmentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CompartmentController {

    private final CompartmentValidator compartmentValidator;
    private final CompartmentConverter compartmentConverter;
    private final FridgeService fridgeService;
    private final CompartmentService compartmentService;
    private final UserService userService;

    @InitBinder("saveCompartmentDto")
    public void initCompartmentDtoBinder(WebDataBinder webDataBinder){webDataBinder.setValidator(compartmentValidator);}

    @RequestMapping(value = "/api/compartment/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveCompartment(@Validated CompartmentDto compartmentDto, Principal principal){
        User user = userService.getByUsernameOrThrowException(principal.getName());
        Compartment compartment;
        if(compartmentDto.getId() == null || compartmentDto.getId() == 0){
            Optional<Fridge> optionalFridge = fridgeService.findById(compartmentDto.getId());
            if(optionalFridge.isPresent() && fridgeService.fridgeBelongsToUser(optionalFridge.get(), user)){
                compartment = compartmentConverter.toEntity(compartmentDto);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else {
            Optional<Compartment> oldCompartment = compartmentService.findById(compartmentDto.getId());
            if(oldCompartment.isPresent() && compartmentService.validateBelongsToUser(oldCompartment.get(), user)){
                compartment = compartmentService.update(oldCompartment.get(), compartmentDto);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(compartmentConverter.toDto(compartmentService.save(compartment)), HttpStatus.OK);
    }

}
