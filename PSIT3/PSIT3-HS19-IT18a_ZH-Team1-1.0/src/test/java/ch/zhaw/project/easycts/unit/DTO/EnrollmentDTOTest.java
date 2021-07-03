package ch.zhaw.project.easycts.unit.DTO;

import ch.zhaw.project.easycts.DTO.EnrollmentDTO;
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
public class EnrollmentDTOTest {
    private EnrollmentDTO enrollment;

    private final long id = 1L;
    private final int sem = 1;
    private final int ects = 4;
    private final String mg = "mg";
    private final String name = "name";
    private List<Mark> marks;

    @Before
    public void init() {
        enrollment = new EnrollmentDTO(name, marks, ects, mg, id, false);
        marks = new ArrayList<>();
        marks.add(new Mark());
    }

    @Test
    public void testEnrollmentGetterAndSetter() {
        enrollment.setName(name);
        assertThat(enrollment.getName()).isEqualTo(name);

        enrollment.setMarks(marks);
        assertThat(enrollment.getMarks()).isEqualTo(marks);

        enrollment.setEcts(ects);
        assertThat(enrollment.getEcts()).isEqualTo(ects);

        enrollment.setEnrollmentId(id);
        assertThat(enrollment.getEnrollmentId()).isEqualTo(id);

        enrollment.setModuleGroupName(mg);
        assertThat(enrollment.getModuleGroupName()).isEqualTo(mg);

        enrollment.setDispensed(false);
        assertThat(enrollment.isDispensed()).isEqualTo(false);

        assertThat(enrollment.toString()).startsWith("EnrollmentDTO");
    }
}