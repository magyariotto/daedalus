package com.github.magyariotto.daedalus.controller;

import com.github.magyariotto.daedalus.controller.request.CreateAccountRequest;
import com.github.magyariotto.daedalus.database.*;
import com.github.magyariotto.daedalus.errorHandler.ErrorHandlerException;
import com.github.magyariotto.daedalus.generator.PlanetGenerator;
import com.github.magyariotto.daedalus.validation.CreateAccountRequestValidation;
import com.github.magyariotto.encryption.impl.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreateAccountController {
    private final CreateAccountRequestValidation createAccountValidation;
    private final UsersRepository usersRepository;
    private final PlanetsRepository planetsRepository;
    private final BuildingsRepository buildingsRepository;
    private final PlanetGenerator planetGenerator;
    private final PasswordService passwordService;

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
            throw new ErrorHandlerException("The username already exists", HttpStatus.ALREADY_REPORTED, "A felhasznalonev foglalt");
        }

        if (!isNull(searchUserByEmail)) {
            throw new ErrorHandlerException("The e-mail adress already exists", HttpStatus.ALREADY_REPORTED, "Az email cim foglalt");
        }

        Users users = new Users();
        users.setUserId(UUID.randomUUID());
        users.setUserName(createAccountRequest.getUserName());
        users.setFirstName(createAccountRequest.getFirstName());
        users.setLastName(createAccountRequest.getLastName());
        users.setPassword(passwordService.hashPassword(createAccountRequest.getPassword()));
        users.setEmail(createAccountRequest.getEmail());

        PlanetGenerator planetGenerator = new PlanetGenerator();
        Optional<Planets> coordinateOptional;
        String coordinate = null;
        do{
            coordinate = planetGenerator.getCoordinate();
            coordinateOptional = planetsRepository.findByCoordinate(coordinate);
        }while (coordinateOptional.isPresent());

        Planets planets = new Planets();
        planets.setPlanetId(UUID.randomUUID());
        planets.setUserId(users.getUserId());
        planets.setFullSize(planetGenerator.getPlanetSize());
        planets.setExistsSize(0);
        planets.setCoordinate(coordinate);

        Buildings buildings = new Buildings();
        buildings.setPlanetId(planets.getPlanetId());
        buildings.setUserId(users.getUserId());
        buildings.setCovenantLevel(0);
        buildings.setNanoTechnologyLevel(0);
        buildings.setResearchLaboratoryLevel(0);
        buildings.setRocketSilo(0);
        buildings.setRobotFactoryLevel(0);
        buildings.setSpaceDockLevel(0);

        usersRepository.save(users);
        planetsRepository.save(planets);
        buildingsRepository.save(buildings);
    }
}
