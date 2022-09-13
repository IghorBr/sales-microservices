package com.microservices.delivery.service;

import com.microservices.delivery.domain.BaseService;
import com.microservices.delivery.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<User> {

    Optional<User> findByUsername(String username);
}
