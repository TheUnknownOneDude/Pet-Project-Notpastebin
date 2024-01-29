package ru.deniska.notpaste.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.deniska.notpaste.form.PostRequestBody;
import ru.deniska.notpaste.service.UserService;

@Component
public class PostRequestValidator implements Validator {
    private final UserService userService;

    public PostRequestValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return PostRequestBody.class.equals(clazz);
    }


    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostRequestBody postRequestBody = (PostRequestBody) target;
            if (userService.findById(postRequestBody.getUserId()) == null) {
                errors.reject("invalid-user", "Invalid user");
            }
        }
    }
}
