package ch.zhaw.project.easycts.unit;

import ch.zhaw.project.easycts.DTO.CreateModule;
import ch.zhaw.project.easycts.controllers.ModuleController;
import ch.zhaw.project.easycts.model.Mark;
import ch.zhaw.project.easycts.model.User;
import ch.zhaw.project.easycts.services.EnrollmentService;
import ch.zhaw.project.easycts.services.ModuleService;
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

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerTest {

    @Autowired
    private ModuleController moduleController;

    @MockBean
    private ModuleService moduleService;

    @MockBean
    private EnrollmentService enrollmentService;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User user;
    private CreateModule createModule;

    @Before
    public void init() {
        user = new User();
        user.setId(1L);
        user.setRoleList(null);

        Mark mark = new Mark();
        mark.setMark(4);
        mark.setWeighting(2);
        mark.setEnrollmentId(1L);
        mark.setMarkId(1L);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark);

        createModule = new CreateModule();
        createModule.setEnrollmentId(1);
        createModule.setName("PSIT3");
        createModule.setSemester(2);
        createModule.setModulegroup("MG1");
        createModule.setEcts(4);
        createModule.setDispensed(false);
        createModule.setMarks(markList);
    }

    // ---------- Tests for saveModule() ----------
    @Test
    public void testAddNewModule() {
        // TODO: new enrollment
        assertThat(true).isEqualTo(true);
    }

    @Test
    public void testEditModule() {
        // TODO: edit already existing enrollment
        assertThat(true).isEqualTo(true);
    }
}
