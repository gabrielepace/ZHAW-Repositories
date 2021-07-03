package ch.zhaw.project.easycts.unit.services;

import ch.zhaw.project.easycts.DTO.UserRegistration;
import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.repositories.UserRepository;
import ch.zhaw.project.easycts.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    private final String EMAIL = "alexandra@gmail.com";
    private final String PWD = "1234567";
    private final String WRONG_PWD = "764321";
    private final String EMAIL2 = "ivan@gmail.com";
    private final String PWD2 = "2345678";
    private final String WRONG_PWD2 = "876432";

    private User dummyUser;
    private UserRegistration userData;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    // ---------- Tests for validateUser() ----------
    @Test
    public void testValidUser() {
        when(userRepository.findByUsername("alexandra@gmail.com")).thenReturn(dummyUser);
        assertThat(userService.validateUser(EMAIL, PWD)).isEqualTo(dummyUser);

    }

    @Test
    public void testInvalidUser() {
        when(userRepository.findByUsername("alexandra@gmail.com")).thenReturn(dummyUser);
        assertThat(userService.validateUser(EMAIL, WRONG_PWD)).isEqualTo(null);
    }
}
