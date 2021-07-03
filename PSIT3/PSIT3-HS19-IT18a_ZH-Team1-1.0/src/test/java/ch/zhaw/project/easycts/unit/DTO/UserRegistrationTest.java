package ch.zhaw.project.easycts.unit.DTO;

import ch.zhaw.project.easycts.DTO.UserRegistration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationTest {

    private UserRegistration ur;
    private final String name = "name";
    private final String pwd = "pwd";

    @Before
    public void init() {
        ur = new UserRegistration();
    }

    @Test
    public void testUserRegistrationGetterAndSetter() {
        ur.setUsername(name);
        assertThat(ur.getUsername()).isEqualTo(name);

        ur.setPassword(pwd);
        assertThat(ur.getPassword()).isEqualTo(pwd);

        ur.setPasswordConfirmation(pwd);
        assertThat(ur.getPasswordConfirmation()).isEqualTo(pwd);
    }
}