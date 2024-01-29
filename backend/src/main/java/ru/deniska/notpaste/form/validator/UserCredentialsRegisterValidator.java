package ru.deniska.notpaste.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.deniska.notpaste.form.UserCredentials;
import ru.deniska.notpaste.service.UserService;

@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentials userCredentials = (UserCredentials) target;
            if (userCredentials.getLogin().trim().length() < 4 || userCredentials.getLogin().trim().length() > 25) {
                errors.rejectValue("name", "name.unexpected-size", "size must be from 4 to 25");
                return;
            }
            if (!userService.isLoginVacant(userCredentials.getLogin())) {
                errors.rejectValue("login", "login.is-in-use", "login is in use already");
            }
        }
    }
}