package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.common.response.BaseResponse;
import org.sopt.dto.request.UserSignUpRequest;
import org.sopt.service.UserService;
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

    @PostMapping()
    public BaseResponse<Void> createUser(@RequestBody @Valid UserSignUpRequest req){
        return BaseResponse.ok(userService.createUser(req.name(), req.email()));
    }
}
