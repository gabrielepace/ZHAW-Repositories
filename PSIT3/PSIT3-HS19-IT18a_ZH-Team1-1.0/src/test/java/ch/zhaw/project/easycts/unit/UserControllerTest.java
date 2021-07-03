package ch.zhaw.project.easycts.unit;

import ch.zhaw.project.easycts.DTO.UserRegistration;
import ch.zhaw.project.easycts.controllers.UserController;
import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.services.TokenService;
import ch.zhaw.project.easycts.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private final String EMAIL = "alexandra@gmail.com";
    private final String PWD = "1234567";
    private final String WRONG_PWD = "764321";

    private User dummyUser;
    private UserRegistration userData;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        dummyUser = new User();
        userData = new UserRegistration(EMAIL, PWD, PWD);
    }

    // ---------- Tests for login() ----------
    @Test
    public void testLogin() {
        when(userService.validateUser(EMAIL, PWD)).thenReturn(dummyUser);
        assertThat(userController.login(userData)).contains("token");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLogin() {
        when(userService.validateUser(EMAIL, PWD)).thenReturn(null);
        userController.login(userData);
    }

    // ---------- Tests for logout() ----------
    @Test
    public void testLogout() {
        userController.logout("token");
        verify(tokenService, times(1)).removeAccessToken("token");
    }

    // ---------- Tests for register() ----------
    @Test
    public void testRegister() {
        when(userService.getUser(EMAIL)).thenReturn(null);
        when(userService.save(any())).thenReturn(null);
        assertThat(userController.register(userData)).contains("token");
        verify(userService, times(1)).save(any());
    }

    @Test
    public void testPasswordsNotMatching() {
        reset(userService);
        userData.setPasswordConfirmation(WRONG_PWD);
        try {
            userController.register(userData);
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserController.REGISTER_PWD_NOT_MATCH);
            verify(userService, never()).save(any());
        }
    }

    @Test
    public void testRegisterExistingUser() {
        reset(userService);
        when(userService.getUser(EMAIL)).thenReturn(dummyUser);
        try {
            userController.register(userData);
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserController.REGISTER_EXISTING_NAME);
            verify(userService, never()).save(any());
        }
    }
}
