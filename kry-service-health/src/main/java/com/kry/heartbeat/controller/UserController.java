package com.kry.heartbeat.controller;

import com.kry.heartbeat.dao.UserRepository;
import com.kry.heartbeat.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "User Operation Api")
@RestController
@CrossOrigin()
@RequestMapping("/v1/user")
@Validated
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "returns requested user with e-mail")
    @GetMapping(params = "email")
    public User findByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
}
