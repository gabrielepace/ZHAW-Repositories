package ch.zhaw.project.easycts.controllers;

import ch.zhaw.project.easycts.DTO.UserRegistration;
import ch.zhaw.project.easycts.model.Role;
import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.services.TokenService;
import ch.zhaw.project.easycts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * The {@link UserController} is the REST interface for all user related requests from external applications
 */
@RestController
public class UserController {

    private static final String LOGIN_FAIL = "no such user";
    private static final String LOGOUT = "logged out";
    public static final String REGISTER_PWD_NOT_MATCH = "Passwords do not match";
    public static final String REGISTER_EXISTING_NAME = "User already exists";

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * Registers a new {@link User} and returns a token for the given user
     *
     * @param userRegistration  information about the user
     * @return usertoken
     */
    @CrossOrigin
    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration) {
        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
            throw new IllegalArgumentException(REGISTER_PWD_NOT_MATCH);
        } else if (userService.getUser(userRegistration.getUsername()) != null) {
            throw new IllegalArgumentException(REGISTER_EXISTING_NAME);
        }
        User newUser = new User(userRegistration.getUsername(), userRegistration.getPassword(), Arrays.asList(new Role("USER")));
        newUser = userService.save(newUser);
        return tokenService.addTokenForUser(newUser);
    }

    @CrossOrigin //for ajax from one port to another
    @PutMapping(value = "/login", params = "logout")
    public String logout(@RequestBody String token) {
        tokenService.removeAccessToken(token);
        return LOGOUT;
    }

    /**
     * Validates username and password, if correct returns the user token, else throws exception
     *
     * @param user    information about the user to log in
     * @return token
     */
    @CrossOrigin //for ajax from one port to another
    @PostMapping(value = "/login")
    public String login(@RequestBody UserRegistration user) {
        User us = userService.validateUser(user.getUsername(), user.getPassword());
        if (us != null) {
            return tokenService.addTokenForUser(us);
        } else {
            throw new IllegalArgumentException(LOGIN_FAIL);
        }
    }
}
