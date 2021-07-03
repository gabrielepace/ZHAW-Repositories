package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.Role;
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
public class RoleTest {

    private Role role;
    private final long id = 1L;

    @Before
    public void init() {
        role = new Role();
    }


    @Test
    public void testRoleGetterAndSetter() {
        role.setId(id);
        assertThat(role.getId()).isEqualTo(id);

        role.setName("name");
        assertThat(role.getName()).isEqualTo("name");
    }

}