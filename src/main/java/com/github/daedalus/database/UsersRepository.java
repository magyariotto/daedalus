package com.github.daedalus.database;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsersRepository extends CrudRepository<Users, UUID> {
    Users findByEmail(String email);
    Users findByUserName(String userName);
}
