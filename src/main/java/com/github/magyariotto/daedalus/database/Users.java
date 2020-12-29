package com.github.magyariotto.daedalus.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Users {
    @Id
    private UUID userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
