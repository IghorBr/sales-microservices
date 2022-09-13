package com.microservices.delivery.controller;

import com.microservices.delivery.domain.BaseController;
import com.microservices.delivery.dto.UserDTO;
import com.microservices.delivery.entity.User;
import com.microservices.delivery.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Responsável pelos usuários")
public class UserController extends BaseController<User, UserDTO> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(User.class, UserDTO.class, userService);
        this.userService = userService;
    }
}
