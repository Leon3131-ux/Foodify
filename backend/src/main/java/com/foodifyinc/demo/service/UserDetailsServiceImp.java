package com.foodifyinc.demo.service;

import com.foodifyinc.demo.domain.Permission;
import com.foodifyinc.demo.domain.User;
import com.foodifyinc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Permission> permissions = user.getRoles().stream().flatMap(role -> role.getPermissions().stream()).collect(Collectors.toList());
        for (Permission permission : permissions){
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName().toString()));
        }
        return grantedAuthorities;
    }
}
