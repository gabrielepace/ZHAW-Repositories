package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User save(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User validateUser(String username, String password) {
        User user = getUser(username);
        boolean validPw = false;
        if (user != null) {
            validPw = getPasswordEncoder().matches(password, user.getPassword());
        }
        return validPw ? user : null;
    }
}
