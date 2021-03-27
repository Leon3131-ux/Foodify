package com.foodifyinc.demo.controller;

import com.foodifyinc.demo.Model.RegisterModel;
import com.foodifyinc.demo.domain.RoleName;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.repository.RoleRepository;
import com.foodifyinc.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.foodifyinc.demo.repository.*;

import java.util.Collections;

@Controller
public class UserController {

    private final UserRepository UserRepository;
    private final RoleRepository RoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository) {
        UserRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        RoleRepository = roleRepository;
    }


    @RequestMapping(value = "/api/register", method = RequestMethod.GET)
    public ResponseEntity<String> RegisterUser(RegisterModel model) {
        var finduser = UserRepository.findByUsername(model.Username);
        if(!finduser.isEmpty()) {
            return new ResponseEntity<>("1", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        var role = RoleRepository.findByRoleName(RoleName.USER);
        User newUser = new User(model.Username,model.Email,bCryptPasswordEncoder.encode(model.Password), role);
        UserRepository.save(newUser);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
