package com.microservices.bank.repository;

import com.microservices.bank.domain.BaseRepository;
import com.microservices.bank.entity.Account;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends BaseRepository<Account> {
}
