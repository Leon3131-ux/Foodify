package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.FoodItemConverter;
import com.foodifyinc.demo.domain.FoodItem;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.FoodItemDto;
import com.foodifyinc.demo.service.FoodItemService;
import com.foodifyinc.demo.service.UserService;
import com.foodifyinc.demo.validator.FoodItemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemValidator foodItemValidator;
    private final FoodItemConverter foodItemConverter;
    private final FoodItemService foodItemService;
    private final UserService userService;

    @InitBinder("foodItemDto")
    public void setFoodItemDtoBinder(WebDataBinder binder){binder.setValidator(foodItemValidator);}

    @RequestMapping(value = "/api/foodItem/save")
    public ResponseEntity<?> saveFoodItem(@RequestBody @Validated FoodItemDto foodItemDto, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        FoodItem foodItem;
        if(foodItemDto.getId() == 0 || foodItemDto.getId() == null){
            foodItem = foodItemConverter.toEntity(foodItemDto, user);
        }else{
            Optional<FoodItem> oldFoodItem = foodItemService.findById(foodItemDto.getId());
            if(oldFoodItem.isPresent() && oldFoodItem.get().getUser().equals(user)){
                foodItem = foodItemService.update(oldFoodItem.get(), foodItemDto);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(foodItemConverter.toDto(foodItemService.save(foodItem)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/foodItem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodItem(@PathVariable(value = "id") FoodItem foodItem, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        if(foodItem.getUser().equals(user)){
            foodItemService.delete(foodItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
