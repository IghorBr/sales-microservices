package com.microservices.bank.service.impl;

import com.microservices.bank.domain.BaseRepository;
import com.microservices.bank.domain.BaseServiceImpl;
import com.microservices.bank.entity.User;
import com.microservices.bank.repository.UserRepository;
import com.microservices.bank.service.UserService;
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
