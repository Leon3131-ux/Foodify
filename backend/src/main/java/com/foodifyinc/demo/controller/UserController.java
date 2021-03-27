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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserValidator userValidator;
    private final UserConverter userConverter;
    private final UserService userService;

    @InitBinder("registerDto")
    public void initRegisterDtoBinder(WebDataBinder webDataBinder){webDataBinder.setValidator(userValidator);}

    @RequestMapping(value = SecurityConstants.SIGN_UP_URL, method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDto registerDto){
        User user = userConverter.toEntity(registerDto);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
