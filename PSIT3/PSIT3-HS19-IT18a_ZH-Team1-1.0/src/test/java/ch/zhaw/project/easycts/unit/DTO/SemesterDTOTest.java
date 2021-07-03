package ch.zhaw.project.easycts.unit.DTO;

import ch.zhaw.project.easycts.DTO.EnrollmentDTO;
import ch.zhaw.project.easycts.DTO.SemesterDTO;
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
public class SemesterDTOTest {

    private SemesterDTO semester;
    private EnrollmentDTO enrollment;
    private List<EnrollmentDTO> enrollments;
    private final int sem = 1;

    @Before
    public void init() {
        semester = new SemesterDTO(sem);
        enrollment = new EnrollmentDTO("name", new ArrayList<>(), 4, "mg", 1L, false);
        enrollments = new ArrayList<>();
        enrollments.add(enrollment);
    }

    @Test
    public void testSemesterGetterAndSetter() {
        semester.setSemesterId(sem);
        assertThat(semester.getSemesterId()).isEqualTo(sem);

        semester.setEnrollments(enrollments);
        assertThat(semester.getEnrollments()).isEqualTo(enrollments);

        semester.addEnrollment(enrollment);
        assertThat(semester.getEnrollments().size()).isEqualTo(2);
    }
}