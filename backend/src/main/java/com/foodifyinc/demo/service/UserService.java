package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getByUsernameOrThrowException(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


}
