package ru.deniska.notpaste.controller;


import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.deniska.notpaste.domain.Post;
import ru.deniska.notpaste.domain.User;
import ru.deniska.notpaste.exception.ValidationException;
import ru.deniska.notpaste.form.PostRequestBody;
import ru.deniska.notpaste.form.validator.PostRequestValidator;
import ru.deniska.notpaste.service.JwtService;
import ru.deniska.notpaste.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/api/1/posts")
public class PostController {
    private final PostService postService;
    private final PostRequestValidator postRequestValidator;
    private final JwtService jwtService;

    public PostController(PostService postService, PostRequestValidator postRequestValidator, JwtService jwtService) {
        this.postService = postService;
        this.postRequestValidator = postRequestValidator;
        this.jwtService = jwtService;
    }

    @InitBinder("userCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(postRequestValidator);
    }

    @GetMapping("post/{id}")
    public Post findPost(@PathVariable String id) {
        return postService.postById(Long.parseLong(id));
    }

    @GetMapping("posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("posts10")
    public List<Post> find10() {
        return postService.find10();
    }

    @GetMapping("usersPosts")
    public List<Post> findUsersPosts(@RequestParam String userId) {
        return postService.usersPosts(Long.parseLong(userId));
    }

    @PostMapping("createPost")
    public void createPost(@Valid @RequestBody PostRequestBody postRequestBody, @RequestParam String jwt,
                           BindingResult bindingResult) {

        checkErrors(bindingResult);
        checkJwt(jwt, bindingResult);

        postService.saveRB(postRequestBody);
    }

    @PostMapping("editPost")
    public void editPost(@RequestParam String jwt, @Valid @RequestBody Post post,
                          BindingResult bindingResult) {

        checkErrors(bindingResult);
        checkJwt(jwt, bindingResult);

        postService.save(post);
    }

    @GetMapping("search/{query}")
    public List<Post> search(@PathVariable String query) {
        return postService.searchPost(query);
    }



    private void checkJwt(String jwt, BindingResult bindingResult) {
        User user = jwtService.find(jwt);
        if (user == null) {
            bindingResult.addError(new ObjectError("no-user", "You should be logged"));
            throw new ValidationException(bindingResult);
        }
    }

    private void checkErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
