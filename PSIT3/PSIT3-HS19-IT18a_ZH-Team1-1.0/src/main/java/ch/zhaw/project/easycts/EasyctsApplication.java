package ch.zhaw.project.easycts;

import ch.zhaw.project.easycts.config.CustomUserDetails;
import ch.zhaw.project.easycts.model.Role;
import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.repositories.UserRepository;
import ch.zhaw.project.easycts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * Startup class of the backend server
 */
@SpringBootApplication(scanBasePackages = "ch.zhaw.project.easycts")
public class EasyctsApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EasyctsApplication.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
        if (repository.count() == 0)
            userService.save(new User("user", "password", Arrays.asList(new Role("USER"), new Role("ACTUATOR"), new Role("ADMIN"))));
        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    private UserDetailsService userDetailsService(final UserRepository repository) {
        return username -> new CustomUserDetails(repository.findByUsername(username));
    }
}
