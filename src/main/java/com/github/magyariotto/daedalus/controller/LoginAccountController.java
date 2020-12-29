package com.github.magyariotto.daedalus.controller;

import com.github.magyariotto.daedalus.config.ConfigProperties;
import com.github.magyariotto.daedalus.controller.request.LoginAccountRequest;
import com.github.magyariotto.daedalus.database.LoginSession;
import com.github.magyariotto.daedalus.database.Users;
import com.github.magyariotto.daedalus.database.UsersRepository;
import com.github.magyariotto.daedalus.errorHandler.ErrorHandlerException;
import com.github.magyariotto.daedalus.service.LoginSessionService;
import com.github.magyariotto.daedalus.validation.LoginAccountRequestValidation;
import com.github.magyariotto.encryption.impl.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@RestController
public class LoginAccountController {
    private final UsersRepository usersRepository;
    private final LoginAccountRequestValidation loginAccountRequestValidation;
    private final LoginSessionService loginSessionService;
    private final ConfigProperties configProperties;
    private final PasswordService passwordService;

    @PostMapping("/login")
    public void login(@RequestBody LoginAccountRequest loginAccountRequest, HttpServletResponse response){
        log.info("Login request test: {}", loginAccountRequest);
        boolean loginAccountValidation = loginAccountRequestValidation.loginAccountRequestValidation(loginAccountRequest);
        if(!loginAccountValidation){
            log.error("Invalid parameters.");
            throw new IllegalArgumentException("Invalid parameters.");
        }
        Users actualUser = null;
        Users searchUserByUserName = usersRepository.findByUserName(loginAccountRequest.getUserNameOrEmail());
        Users searchUserByEmail = usersRepository.findByEmail(loginAccountRequest.getUserNameOrEmail());

        if(isNull(searchUserByUserName) && isNull(searchUserByEmail)){
            throw new ErrorHandlerException("User not exists.", HttpStatus.UNAUTHORIZED,"User not exists.");
        }

        if(!isNull(searchUserByUserName)){
            actualUser = searchUserByUserName;
        }

        if(!isNull(searchUserByEmail)){
            actualUser = searchUserByEmail;
        }

        if(!passwordService.authenticate(loginAccountRequest.getPassword(),actualUser.getPassword())){
            throw new ErrorHandlerException("Incorrect password.", HttpStatus.UNAUTHORIZED, "Incorrect password.");
        }

        LoginSession loginSession = loginSessionService.createSession(actualUser.getUserId(),loginAccountRequest.isRemember());

        Cookie cookie = new Cookie("session-id", loginSession.getSessionId().toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        int expiry = loginSession.isRemember() ? Integer.MAX_VALUE : configProperties.getSessionExpirationSeconds();
        cookie.setMaxAge(expiry);

        response.addCookie(cookie);
    }
}
