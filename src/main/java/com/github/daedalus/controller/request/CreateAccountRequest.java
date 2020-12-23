package com.github.daedalus.controller.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
