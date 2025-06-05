package org.sopt.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.user.dto.response.UserLoginResponse;
import org.sopt.global.exception.CustomException;
import org.sopt.domain.user.model.User;
import org.sopt.domain.user.repository.UserRepository;
import org.sopt.global.jwt.JwtProvider;
import org.springframework.stereotype.Service;

import static org.sopt.global.exception.ErrorCode.EMAIL_ALREADY_EXISTS;
import static org.sopt.global.exception.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public Void createUser(String name, String email){
        if(userRepository.existsByEmail(email)){
            throw new CustomException(EMAIL_ALREADY_EXISTS);
        }
        userRepository.save(new User(name, email));
        return null;
    }

    public UserLoginResponse login(String email){
        User loginUser = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        String accessToken = JwtProvider.generateToken(loginUser.getId());

        return new UserLoginResponse(loginUser.getId(), accessToken);

    }
}
