package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.FridgeConverter;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.FridgeDto;
import com.foodifyinc.demo.service.FridgeService;
import com.foodifyinc.demo.service.UserService;
import com.foodifyinc.demo.validator.FridgeValidator;
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
public class FridgeController {

    private final UserService userService;
    private final FridgeService fridgeService;
    private final FridgeValidator fridgeValidator;
    private final FridgeConverter fridgeConverter;

    @InitBinder("fridgeDto")
    public void initFridgeDtoBinder(WebDataBinder binder){binder.setValidator(fridgeValidator);}

    @RequestMapping(value = "/api/fridge/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveFridge(@RequestBody @Validated FridgeDto fridgeDto, Principal principal){
        User user = userService.getByUsernameOrThrowException(principal.getName());
        Fridge fridge;
        if(fridgeDto.getId() == null || fridgeDto.getId() == 0){
            fridge = fridgeConverter.toEntity(fridgeDto, user);
        }else{
            Optional<Fridge> oldFridge = fridgeService.findById(fridgeDto.getId());
            if(oldFridge.isPresent() && fridgeService.fridgeBelongsToUser(oldFridge.get(),user)){
                fridge = fridgeService.update(oldFridge.get(), fridgeDto);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(fridgeConverter.toDto(fridgeService.save(fridge)), HttpStatus.OK);
    }

}
