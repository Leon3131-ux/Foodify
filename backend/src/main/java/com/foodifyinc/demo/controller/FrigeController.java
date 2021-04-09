package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.converter.UserConverter;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.RegisterDto;
import com.foodifyinc.demo.security.SecurityConstants;
import com.foodifyinc.demo.service.UserService;
import com.foodifyinc.demo.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class FrigeController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @RequestMapping(value = "api/frige/getfrige",method = RequestMethod.GET)
    public ResponseEntity<?> getfrige(){
        //authenticationManager.getUser
        //responds Friges
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
