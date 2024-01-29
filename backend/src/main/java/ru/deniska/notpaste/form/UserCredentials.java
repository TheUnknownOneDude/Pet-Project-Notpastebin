package ru.deniska.notpaste.form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentials {
    @NotEmpty
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z]{2,25}", message = "Expected Latin letters")
    private String login;

    @NotEmpty
    @Size(min = 1, max = 60)
    private String password;
}
