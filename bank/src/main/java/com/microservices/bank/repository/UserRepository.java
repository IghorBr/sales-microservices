package com.microservices.bank.repository;

import com.microservices.bank.domain.BaseRepository;
import com.microservices.bank.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);
}
