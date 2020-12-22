package com.github.daedalus.controller;

import com.github.daedalus.controller.request.CreateAccountRequest;
import com.github.daedalus.database.Users;
import com.github.daedalus.database.UsersRepository;
import com.github.daedalus.errorHandler.ErrorHandlerException;
import com.github.daedalus.validation.CreateAccountValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreateAccountController {
    private final CreateAccountValidation createAccountValidation;
    private final UsersRepository usersRepository;

    @PostMapping("/create_account")
    public void createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        boolean createdAccountValidation = createAccountValidation.createAccountValidation(createAccountRequest);
        if(!createdAccountValidation){
            log.error("Nem megfelelo adatok.");
            throw new IllegalArgumentException("Nem megfelelo adatok.");
        }

        Users searchUserByEmail = usersRepository.findByEmail(createAccountRequest.getEmail());
        Users searchUserByUsername = usersRepository.findByUsername(createAccountRequest.getUsername());

        if(isNull(searchUserByEmail)){
            throw new ErrorHandlerException("Az email cim foglalt", HttpStatus.ALREADY_REPORTED, "Az email cim foglalt");
        }

        if(isNull(searchUserByUsername)){
            throw new ErrorHandlerException("A felhasznalonev foglalt", HttpStatus.ALREADY_REPORTED, "A felhasznalonev foglalt");
        }

        Users users = new Users();
        users.setUserId(UUID.randomUUID());
        users.setUsername(createAccountRequest.getUsername());
        users.setFirstName(createAccountRequest.getFirstName());
        users.setLastName(createAccountRequest.getLastName());
        users.setPassword(createAccountRequest.getPassword());
        users.setEmail(createAccountRequest.getEmail());

        usersRepository.save(users);
    }
}
