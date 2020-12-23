package com.github.daedalus.validation;

import com.github.daedalus.controller.request.CreateAccountRequest;
import com.github.daedalus.database.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RequiredArgsConstructor
@Component
public class CreateAccountValidation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final UsersRepository usersRepository;

    public boolean createAccountValidation(CreateAccountRequest createAccountRequest){
        // email Validation:
        if(isBlank(createAccountRequest.getEmail())){
            return false;
        }
        if(!EMAIL_PATTERN.matcher(createAccountRequest.getEmail()).matches()){
            return false;
        }

        // firstName Validation:
        if(isBlank(createAccountRequest.getFirstName())){
            return false;
        }

        // lastName Validation:
        if(isBlank(createAccountRequest.getLastName())){
            return false;
        }

        // username Validation:
        if(createAccountRequest.getUserName().length() < 6 || createAccountRequest.getUserName().length() > 20){
            return false;
        }

        // password Validation:
        if(isNull(createAccountRequest.getPassword())){
            return false;
        }

        if(createAccountRequest.getPassword().length() < 5 || createAccountRequest.getPassword().length() > 20){
            return false;
        }

        return true;
    }
}
