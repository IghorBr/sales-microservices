package com.microservices.sales.service.impl;

import com.microservices.sales.domain.BaseServiceImpl;
import com.microservices.sales.entity.User;
import com.microservices.sales.repository.UserRepository;
import com.microservices.sales.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
