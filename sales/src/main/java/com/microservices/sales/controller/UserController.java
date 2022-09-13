package com.microservices.sales.controller;

import com.microservices.sales.domain.BaseController;
import com.microservices.sales.dto.UserDTO;
import com.microservices.sales.entity.User;
import com.microservices.sales.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Responsável por Usuários")
public class UserController extends BaseController<User, UserDTO> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(User.class, UserDTO.class, userService);
        this.userService = userService;
    }
}
