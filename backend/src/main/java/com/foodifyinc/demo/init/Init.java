package com.foodifyinc.demo.init;

import com.foodifyinc.demo.domain.*;
import com.foodifyinc.demo.repository.UnitRepository;
import com.foodifyinc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Init {

    private final UserRepository userRepository;
    private final UnitRepository unitRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${db.initialize}")
    private boolean initialize;

    @PostConstruct
    public void init(){
        if(initialize){
            loadTestUsers();
            loadUnits();
        }
    }

    private void loadTestUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("test", "test@test.com", bCryptPasswordEncoder.encode("test")));

        for(User user: users){
            if(userRepository.findByUsername(user.getUsername()).isEmpty()){
                userRepository.save(user);
            }
        }
    }

    private void loadUnits(){
        for(UnitName unitName: UnitName.values()){
            if(unitRepository.findByName(unitName).isEmpty()){
                unitRepository.save(new Unit(unitName));
            }
        }
    }

}
