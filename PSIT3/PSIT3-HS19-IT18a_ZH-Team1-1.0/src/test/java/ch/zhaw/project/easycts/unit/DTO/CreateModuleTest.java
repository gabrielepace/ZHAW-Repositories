package ch.zhaw.project.easycts.unit.DTO;

import ch.zhaw.project.easycts.DTO.CreateModule;
import ch.zhaw.project.easycts.model.Mark;
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
public class CreateModuleTest {

    private CreateModule cm;

    private final long id = 1L;
    private final int sem = 1;
    private final int ects = 4;
    private final String mg = "mg";
    private final String name = "name";
    private final String token = "token";
    private List<Mark> marks;

    @Before
    public void init() {
        cm = new CreateModule();
        marks = new ArrayList<>();
        marks.add(new Mark());
    }

    @Test
    public void testCreateModuleGetterAndSetter() {
        cm.setEnrollmentId(id);
        assertThat(cm.getEnrollmentId()).isEqualTo(id);

        cm.setName(name);
        assertThat(cm.getName()).isEqualTo(name);

        cm.setSemester(sem);
        assertThat(cm.getSemester()).isEqualTo(sem);

        cm.setModulegroup(mg);
        assertThat(cm.getModulegroup()).isEqualTo(mg);

        cm.setEcts(ects);
        assertThat(cm.getEcts()).isEqualTo(ects);

        cm.setDispensed(false);
        assertThat(cm.isDispensed()).isEqualTo(false);

        cm.setMarks(marks);
        assertThat(cm.getMarks()).isEqualTo(marks);

        cm.setToken(token);
        assertThat(cm.getToken()).isEqualTo(token);

        assertThat(cm.toString()).startsWith("CreateModule");
    }

}