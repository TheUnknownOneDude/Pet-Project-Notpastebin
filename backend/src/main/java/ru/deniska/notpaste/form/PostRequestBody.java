package ru.deniska.notpaste.form;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.deniska.notpaste.domain.Post;
import ru.deniska.notpaste.domain.User;

import java.io.Serializable;


@Getter
@Setter
public class PostRequestBody implements Serializable {

    @NotBlank
    @Size(min = 4, max = 100)
    private String title;

    @NotBlank
    @Size(min = 1, max = 65536)
    private String text;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Post.Publicity publicity;

    private long userId;

    @Min(-1)
    @Max(365)
    private int deletionInterval;

}
