package com.github.daedalus.service;

import com.github.daedalus.config.ConfigProperties;
import com.github.daedalus.database.LoginSession;
import com.github.daedalus.database.LoginSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class LoginSessionService {
    private final LoginSessionRepository loginSessionRepository;
    private final ConfigProperties configProperties;

    public LoginSession createSession(UUID userId,boolean remember){
        LoginSession loginSession = new LoginSession();
        loginSession.setUserId(userId);
        loginSession.setLastAccess(LocalDateTime.now());
        loginSession.setRemember(remember);
        loginSession.setSessionId(UUID.randomUUID());

        loginSessionRepository.save(loginSession);
        return loginSession;
    }
}
