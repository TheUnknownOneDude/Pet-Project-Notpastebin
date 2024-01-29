package ru.deniska.notpaste.service;

import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.deniska.notpaste.domain.Post;
import ru.deniska.notpaste.form.PostRequestBody;
import ru.deniska.notpaste.repository.PostRepository;
import java.util.List;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public List<Post> find10() {
        return postRepository.findAllByOrderByCreationTimeDesc(Limit.of(10));
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void saveRB(PostRequestBody postRequestBody) {
        Post post = new Post();
        fillPost(post, postRequestBody);
        postRepository.save(post);
    }

    @Scheduled(fixedRate = 600000)
    public void CheckExpiration() {
        postRepository.deleteOutDated();
    }

    public List<Post> usersPosts(long id) {
        return postRepository.findAllByUser_Id(id);
    }

    public Post postById(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public List<Post> searchPost(String query) {
        return postRepository.searchPostByTitle(query);
    }


    private void fillPost(Post post, PostRequestBody postRequestBody) {
        post.setTitle(postRequestBody.getTitle());
        post.setText(postRequestBody.getText());
        post.setPublicity(postRequestBody.getPublicity());
        post.setUser(userService.findById(postRequestBody.getUserId()));
        post.setDeletionInterval(postRequestBody.getDeletionInterval());
    }
}
