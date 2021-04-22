package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.FridgeConverter;
import com.foodifyinc.demo.domain.Fridge;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.ReturnFridgeDto;
import com.foodifyinc.demo.dto.SaveFridgeDto;
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
import java.util.List;
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
    public ResponseEntity<?> saveFridge(@RequestBody @Validated SaveFridgeDto saveFridgeDto, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        Fridge fridge;
        if(saveFridgeDto.getId() == null || saveFridgeDto.getId() == 0){
            fridge = fridgeConverter.toEntity(saveFridgeDto, user);
        }else{
            Optional<Fridge> oldFridge = fridgeService.findById(saveFridgeDto.getId());
            if(oldFridge.isPresent() && fridgeService.fridgeBelongsToUser(oldFridge.get(),user)){
                fridge = fridgeService.update(oldFridge.get(), saveFridgeDto);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(fridgeConverter.toDto(fridgeService.save(fridge)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/fridges", method = RequestMethod.GET)
    public List<ReturnFridgeDto> getFridges(Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        return fridgeConverter.toDtos(fridgeService.findByUser(user));
    }

    @RequestMapping(value = "/api/fridge/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFridge(@PathVariable(value = "id") Fridge fridge, Principal principal){
        User user = userService.findByUsernameOrThrowException(principal.getName());
        if(fridge.getUser().equals(user)){
            return new ResponseEntity<>(fridgeConverter.toDto(fridge), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
