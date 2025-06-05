package org.sopt.domain.user.controller;

import jakarta.validation.Valid;
import org.sopt.domain.user.dto.request.UserLoginRequest;
import org.sopt.domain.user.dto.request.UserSignUpRequest;
import org.sopt.domain.user.dto.response.UserLoginResponse;
import org.sopt.domain.user.service.UserService;
import org.sopt.global.response.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public BaseResponse<Void> createUser(@RequestBody @Valid UserSignUpRequest req){
        return BaseResponse.ok(userService.createUser(req.name(), req.email()));
    }

    @PostMapping("login")
    public BaseResponse<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest req){
        return BaseResponse.ok(userService.login(req.email()));
    }
}
