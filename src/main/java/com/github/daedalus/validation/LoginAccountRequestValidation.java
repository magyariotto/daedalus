package com.github.daedalus.validation;

import com.github.daedalus.controller.request.LoginAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RequiredArgsConstructor
@Component
public class LoginAccountRequestValidation {
    public boolean loginAccountRequestValidation(LoginAccountRequest loginAccountRequest) {
        // Username or Email Validation
        if (isBlank(loginAccountRequest.getUserNameOrEmail())) {
            return false;
        }
        // Password Validation
        if (isNull(loginAccountRequest.getPassword())) {
            return false;
        }
        return true;
    }
}
