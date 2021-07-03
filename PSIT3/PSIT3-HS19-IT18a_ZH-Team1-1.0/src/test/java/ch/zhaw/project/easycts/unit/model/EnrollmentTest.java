package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.Enrollment;
import ch.zhaw.project.easycts.model.Mark;
import ch.zhaw.project.easycts.model.Module;
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
public class EnrollmentTest {

    private Enrollment enrollment;
    private Module module;
    private User user;

    private List<Mark> marks;
    private final long id = 1L;
    private final int semester = 1;

    @Before
    public void init() {
        enrollment = new Enrollment();
        user = new User();
        module = new Module("PSIT");
        Mark mark = new Mark();
        marks = new ArrayList<>();
        marks.add(mark);
    }

    @Test
    public void testEnrollmentGetterAndSetter() {
        enrollment.setEnrollmentId(id);
        assertThat(enrollment.getEnrollmentId()).isEqualTo(id);

        enrollment.setCreator(user);
        assertThat(enrollment.getCreator()).isEqualTo(user);

        enrollment.setModule(module);
        assertThat(enrollment.getModule()).isEqualTo(module);

        enrollment.setMarks(marks);
        assertThat(enrollment.getMarks()).isEqualTo(marks);

        enrollment.setSemester(semester);
        assertThat(enrollment.getSemester()).isEqualTo(semester);

        enrollment.setDispensed(false);
        assertThat(enrollment.isDispensed()).isEqualTo(false);
    }

}