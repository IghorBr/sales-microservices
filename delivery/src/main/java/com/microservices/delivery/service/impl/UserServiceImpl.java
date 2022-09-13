package com.microservices.delivery.service.impl;

import com.microservices.delivery.domain.BaseRepository;
import com.microservices.delivery.domain.BaseServiceImpl;
import com.microservices.delivery.entity.User;
import com.microservices.delivery.repository.UserRepository;
import com.microservices.delivery.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
