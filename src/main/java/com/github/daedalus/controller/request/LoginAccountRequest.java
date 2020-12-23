package com.github.daedalus.controller.request;

import lombok.Data;

@Data
public class LoginAccountRequest {
    private String userNameOrEmail;
    private String password;
    private boolean remember;
}
