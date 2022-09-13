package com.microservices.bank.service;

import com.microservices.bank.domain.BaseService;
import com.microservices.bank.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<User> {

    Optional<User> findByUsername(String username);
}
