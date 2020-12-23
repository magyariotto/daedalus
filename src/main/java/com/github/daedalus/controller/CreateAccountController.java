package com.github.daedalus.controller;

import com.github.daedalus.controller.request.CreateAccountRequest;
import com.github.daedalus.database.Users;
import com.github.daedalus.database.UsersRepository;
import com.github.daedalus.errorHandler.ErrorHandlerException;
import com.github.daedalus.validation.CreateAccountRequestValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.util.Objects.isNull;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreateAccountController {
    private final CreateAccountRequestValidation createAccountValidation;
    private final UsersRepository usersRepository;

    @PostMapping("/create_account")
    public void createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        boolean createdAccountValidation = createAccountValidation.createAccountValidation(createAccountRequest);
        if (!createdAccountValidation) {
            log.error("Invalid create account parameters.");
            throw new IllegalArgumentException("Invalid create account parameters.");
        }

        Users searchUserByEmail = usersRepository.findByEmail(createAccountRequest.getEmail());
        Users searchUserByUserName = usersRepository.findByUserName(createAccountRequest.getUserName());

        if (!isNull(searchUserByUserName)) {
            throw new ErrorHandlerException("The username is busy.", HttpStatus.ALREADY_REPORTED, "A felhasznalonev foglalt");
        }

        if (!isNull(searchUserByEmail)) {
            throw new ErrorHandlerException("The e-mail adress is busy", HttpStatus.ALREADY_REPORTED, "Az email cim foglalt");
        }

        Users users = new Users();
        users.setUserId(UUID.randomUUID());
        users.setUserName(createAccountRequest.getUserName());
        users.setFirstName(createAccountRequest.getFirstName());
        users.setLastName(createAccountRequest.getLastName());
        users.setPassword(createAccountRequest.getPassword());
        users.setEmail(createAccountRequest.getEmail());

        usersRepository.save(users);
    }
}
