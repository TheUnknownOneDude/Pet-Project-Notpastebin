package ru.deniska.notpaste.controller;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.deniska.notpaste.domain.User;
import ru.deniska.notpaste.exception.ValidationException;
import ru.deniska.notpaste.form.UserCredentials;
import ru.deniska.notpaste.form.validator.UserCredentialsRegisterValidator;
import ru.deniska.notpaste.service.JwtService;
import ru.deniska.notpaste.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/1/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserCredentialsRegisterValidator registerValidator;

    public UserController(UserService userService, JwtService jwtService, UserCredentialsRegisterValidator registerValidator) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.registerValidator = registerValidator;
    }

    @InitBinder("userCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerValidator);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable String id) {
        return userService.findById(Long.parseLong(id));
    }

    @GetMapping("auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }

    @PostMapping("register")
    public String registerRB(@Valid @RequestBody UserCredentials userCredentials,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        return jwtService.create(userService.register(userCredentials));
    }

}
