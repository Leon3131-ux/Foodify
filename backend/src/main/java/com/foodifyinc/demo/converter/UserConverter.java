package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User toEntity(RegisterDto registerDto){
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        return user;
    }

}
