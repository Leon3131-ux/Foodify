package com.foodifyinc.demo.converter;

import com.foodifyinc.demo.domain.Role;
import com.foodifyinc.demo.domain.RoleName;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.dto.RegisterDto;
import com.foodifyinc.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public User toEntity(RegisterDto registerDto){
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.USER);
        optionalRole.ifPresent(role -> user.setRoles(Collections.singleton(role)));
        return user;
    }

}
