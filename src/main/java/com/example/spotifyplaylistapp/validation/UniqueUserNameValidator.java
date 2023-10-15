package com.example.spotifyplaylistapp.validation;

import com.example.spotifyplaylistapp.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {

    private final UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.findByUserName(value).isEmpty();
    }
}
