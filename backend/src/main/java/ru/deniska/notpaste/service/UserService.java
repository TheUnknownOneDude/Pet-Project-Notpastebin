package ru.deniska.notpaste.service;

import org.springframework.stereotype.Service;
import ru.deniska.notpaste.domain.User;
import ru.deniska.notpaste.form.UserCredentials;
import ru.deniska.notpaste.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }
    
    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin().trim());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }
}
