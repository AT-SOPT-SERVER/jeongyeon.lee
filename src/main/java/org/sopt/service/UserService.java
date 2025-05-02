package org.sopt.service;

import org.sopt.common.exception.CustomException;
import org.sopt.domain.User;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import static org.sopt.common.exception.ErrorCode.EMAIL_ALREADY_EXISTS;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Void createUser(String name, String email){
        if(userRepository.existsByEmail(email)){
            throw new CustomException(EMAIL_ALREADY_EXISTS);
        }
        userRepository.save(new User(name, email));
        return null;
    }

}
