package com.foodifyinc.demo.validator;

import com.foodifyinc.demo.dto.RegisterDto;
import com.foodifyinc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegisterDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterDto registerDto = (RegisterDto) target;
        if(registerDto.getUsername().isBlank()){
            errors.rejectValue("username", "errors.register.username.empty");
        }else {
            if(userRepository.findByUsername(registerDto.getUsername()).isPresent()){
                errors.rejectValue("username", "errors.register.username.alreadyExists");
            }
        }

        if(registerDto.getPassword().isBlank()){
            errors.rejectValue("password", "errors.register.password.empty");
        }
        if(!EmailValidator.getInstance().isValid(registerDto.getEmail())){
            errors.rejectValue("email", "errors.register.email.invalid");
        }
    }
}
