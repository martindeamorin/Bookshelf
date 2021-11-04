package com.example.bookshelf.dao;

import com.example.bookshelf.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolDAO extends JpaRepository<Rol, Long>{
    public Rol getRolByName(String name);
}
