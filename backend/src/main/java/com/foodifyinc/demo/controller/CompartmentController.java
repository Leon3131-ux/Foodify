package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.CompartmentConverter;
import com.foodifyinc.demo.domain.Compartment;
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

@RestController
@RequiredArgsConstructor
public class CompartmentController {

    private final CompartmentValidator compartmentValidator;
    private final CompartmentConverter compartmentConverter;
    private final FridgeService fridgeService;
    private final CompartmentService compartmentService;
    private final UserService userService;

    @InitBinder("saveCompartmentDto")
    public void initRegisterDtoBinder(WebDataBinder webDataBinder){webDataBinder.setValidator(compartmentValidator);}

    @RequestMapping(value = "/api/compartment/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCompartment(@Validated CompartmentDto compartmentDto, Principal principal){
        User user = userService.getByUsernameOrThrowException(principal.getName());
        if(fridgeService.fridgeBelongsToUser(compartmentDto.getFridgeId(), user)){
            Compartment compartment = compartmentService.save(compartmentConverter.toEntity(compartmentDto));
            return new ResponseEntity<>(compartmentConverter.toDto(compartment), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
