package com.github.daedalus.service;

import com.github.daedalus.config.ConfigProperties;
import com.github.daedalus.database.LoginSession;
import com.github.daedalus.database.LoginSessionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Component
public class LoginSessionService {
    private final LoginSessionRepository loginSessionRepository;
    private final ConfigProperties configProperties;

    public LoginSession createSession(UUID userId, boolean remember) {
        LoginSession loginSession = new LoginSession();
        loginSession.setUserId(userId);
        loginSession.setLastAccess(LocalDateTime.now());
        loginSession.setRemember(remember);
        loginSession.setSessionId(UUID.randomUUID());

        loginSessionRepository.save(loginSession);
        return loginSession;
    }

    public void deleteSession(UUID sessionId) {
        loginSessionRepository.deleteById(sessionId);
    }

    public Optional<LoginSession> findBySessionId(UUID sessionId) {
        if (isNull(sessionId)) {
            return Optional.empty();
        }
        Optional<LoginSession> maybeSession = loginSessionRepository.findById(sessionId);
        if (maybeSession.isPresent()) {
            LoginSession loginSession = maybeSession.get();
            LocalDateTime lastAccess = loginSession.getLastAccess();
            LocalDateTime currentTime = LocalDateTime.now();
            int expirationSeconds = configProperties.getSessionExpirationSeconds();
            boolean isSessionActive = lastAccess.plusSeconds(expirationSeconds).isAfter(currentTime);
            return isSessionActive ? maybeSession : Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
