package com.example.bookshelf.dao;

import com.example.bookshelf.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
}
