package com.foodifyinc.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public ResponseEntity<String> testMapping(){
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

}
