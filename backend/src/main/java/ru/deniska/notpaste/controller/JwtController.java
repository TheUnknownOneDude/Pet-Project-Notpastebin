package ru.deniska.notpaste.controller;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.deniska.notpaste.domain.User;
import ru.deniska.notpaste.exception.ValidationException;
import ru.deniska.notpaste.form.UserCredentials;
import ru.deniska.notpaste.form.validator.UserCredentialsEnterValidator;
import ru.deniska.notpaste.service.JwtService;
import ru.deniska.notpaste.service.UserService;

@RestController
@RequestMapping("/api/1")
public class JwtController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserCredentialsEnterValidator userCredentialsEnterValidator;

    public JwtController(JwtService jwtService, UserService userService, UserCredentialsEnterValidator userCredentialsEnterValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userCredentialsEnterValidator = userCredentialsEnterValidator;
    }

    @InitBinder("userCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsEnterValidator);
    }

    @PostMapping("jwt")
    public String create(@Valid @RequestBody UserCredentials userCredentials, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        User user = userService.findByLoginAndPassword(userCredentials.getLogin(), userCredentials.getPassword());

        return jwtService.create(user);
    }
}
