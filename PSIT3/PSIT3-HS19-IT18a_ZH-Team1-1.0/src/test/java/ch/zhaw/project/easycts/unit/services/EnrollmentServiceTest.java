package ch.zhaw.project.easycts.unit.services;

import ch.zhaw.project.easycts.model.Enrollment;
import ch.zhaw.project.easycts.repositories.EnrollmentRepository;
import ch.zhaw.project.easycts.services.EnrollmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentS;
    @MockBean
    private EnrollmentRepository enrollmentRep;

    private final long id = 1L;
    private final String name = "name";

    @Test
    public void testEnrollmentService() {
        reset(enrollmentRep);
        when(enrollmentRep.findById(id)).thenReturn(Optional.of(new Enrollment()));
        enrollmentS.getEnrollmentById(id);
        verify(enrollmentRep, times(1)).findById(id);

        enrollmentS.getAllEnrollmentsFromUser(id);
        verify(enrollmentRep, times(1)).findByUserId(id);

        enrollmentS.saveEnrollment(new Enrollment());
        verify(enrollmentRep, times(1)).save(any());

        enrollmentS.deleteEnrollment(id);
        verify(enrollmentRep, times(1)).deleteById(any());
    }
}