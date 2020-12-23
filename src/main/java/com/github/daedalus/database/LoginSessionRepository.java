package com.github.daedalus.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

import java.util.UUID;

public interface LoginSessionRepository extends CrudRepository<LoginSession, UUID> {

}
