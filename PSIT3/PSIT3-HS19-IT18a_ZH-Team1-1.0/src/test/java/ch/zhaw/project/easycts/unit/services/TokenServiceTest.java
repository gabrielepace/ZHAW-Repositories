package ch.zhaw.project.easycts.unit.services;

import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.services.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenServiceTest {

    @Autowired
    private TokenService tokenS;

    @MockBean
    private User user;

    @Test
    public void testModuleGroupService() {
        String s = tokenS.addTokenForUser(user);
        assertThat(tokenS.getUserByToken(s)).isEqualTo(user);
        tokenS.removeAccessToken(s);
    }
}