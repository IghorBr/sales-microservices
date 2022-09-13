package com.microservices.delivery.repository;

import com.microservices.delivery.domain.BaseRepository;
import com.microservices.delivery.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);
}
