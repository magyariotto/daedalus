package com.github.daedalus.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class LoginSession {
    @Id
    public UUID sessionId;
    public UUID userId;
    public boolean remember;
    private LocalDateTime lastAccess;
}
