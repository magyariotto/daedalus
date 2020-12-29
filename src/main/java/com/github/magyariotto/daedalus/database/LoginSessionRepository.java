package com.github.magyariotto.daedalus.database;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface LoginSessionRepository extends CrudRepository<LoginSession, UUID> {

    void deleteByRememberAndLastAccessBefore(boolean remember, LocalDateTime expiration);
}
