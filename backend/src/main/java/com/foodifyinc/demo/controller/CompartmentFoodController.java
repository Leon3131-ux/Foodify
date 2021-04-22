package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.CompartmentFoodConverter;
import com.foodifyinc.demo.domain.Compartment;
import com.foodifyinc.demo.domain.CompartmentFood;
import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.ReturnCompartmentFoodDto;
import com.foodifyinc.demo.dto.SaveCompartmentFoodDto;
import com.foodifyinc.demo.service.CompartmentFoodService;
import com.foodifyinc.demo.service.CompartmentService;
import com.foodifyinc.demo.service.FoodItemService;
import com.foodifyinc.demo.service.UserService;
import com.foodifyinc.demo.validator.CompartmentFoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CompartmentFoodController {

    @InitBinder("saveCompartmentFoodDto")
    public void setSaveCompartmentFoodDtoBinder(WebDataBinder binder){binder.setValidator(compartmentFoodValidator);}

    private final CompartmentService compartmentService;
    private final CompartmentFoodValidator compartmentFoodValidator;
    private final CompartmentFoodService compartmentFoodService;
    private final FoodItemService foodItemService;
    private final UserService userService;
    private final CompartmentFoodConverter compartmentFoodConverter;

    @RequestMapping(value = "/api/compartment/food/save")
    public ResponseEntity<?> saveCompartmentFood(@RequestBody @Validated SaveCompartmentFoodDto saveCompartmentFoodDto, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        CompartmentFood compartmentFood;
        Optional<Compartment> optionalCompartment = compartmentService.findById(saveCompartmentFoodDto.getCompartmentId());
        Optional<FoodItem> optionalFoodItem = foodItemService.findById(saveCompartmentFoodDto.getFoodItemId());

        if(optionalCompartment.isPresent() && optionalFoodItem.isPresent()
                && compartmentService.validateBelongsToUser(optionalCompartment.get(), user)
                && foodItemService.validateBelongsToUser(optionalFoodItem.get(), user)){
            if(saveCompartmentFoodDto.getId() == null || saveCompartmentFoodDto.getId() == 0){
                compartmentFood = compartmentFoodConverter.toEntity(saveCompartmentFoodDto);
            }else{
                Optional<CompartmentFood> oldCompartmentFood = compartmentFoodService.findById(saveCompartmentFoodDto.getId());
                if(oldCompartmentFood.isPresent() && oldCompartmentFood.get().getCompartment().getFridge().getUser().equals(user)){
                    compartmentFood = compartmentFoodService.update(oldCompartmentFood.get(), saveCompartmentFoodDto);
                }else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(compartmentFoodConverter.toDto(compartmentFoodService.save(compartmentFood)), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/compartment/food/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCompartmentFood(@PathVariable(value = "id") CompartmentFood compartmentFood, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        if(compartmentFood.getCompartment().getFridge().getUser().equals(user)){
            compartmentFoodService.delete(compartmentFood);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/api/compartment/food/expiring", method = RequestMethod.GET)
//    public List<ReturnCompartmentFoodDto> getAllExpiringCompartmentFood(Principal principal){
//        User user = userService.findByUsernameOrThrowException(principal.getName());
//
//    }

}
