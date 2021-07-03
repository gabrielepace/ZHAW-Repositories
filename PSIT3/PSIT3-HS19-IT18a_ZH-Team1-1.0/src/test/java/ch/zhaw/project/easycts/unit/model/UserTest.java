package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.Role;
import ch.zhaw.project.easycts.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    private User user;
    private List<Role> rols;
    private final long id = 1L;

    @Before
    public void init() {
        user = new User();
        rols = new ArrayList<>();
        rols.add(new Role("User"));
    }

    @Test
    public void testUserGetterAndSetter() {
        user.setId(id);
        assertThat(user.getId()).isEqualTo(id);

        user.setUsername("name");
        assertThat(user.getUsername()).isEqualTo("name");

        user.setPassword("pwd");
        assertThat(user.getPassword()).isEqualTo("pwd");

        user.setRoleList(rols);
        assertThat(user.getRoleList()).isEqualTo(rols);
    }
}