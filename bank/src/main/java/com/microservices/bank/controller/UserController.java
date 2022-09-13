package com.microservices.bank.controller;

import com.microservices.bank.domain.BaseController;
import com.microservices.bank.domain.BaseService;
import com.microservices.bank.dto.UserDTO;
import com.microservices.bank.entity.User;
import com.microservices.bank.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Responsável por usuários")
public class UserController extends BaseController<User, UserDTO> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(User.class, UserDTO.class, userService);
        this.userService = userService;
    }
}
