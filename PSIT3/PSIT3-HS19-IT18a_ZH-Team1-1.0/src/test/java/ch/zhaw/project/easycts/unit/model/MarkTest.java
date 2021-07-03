package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.Mark;
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
public class MarkTest {

    private Mark mark;
    private final long id = 1L;

    @Before
    public void init() {
        mark = new Mark();
    }

    @Test
    public void testMarkGetterAndSetter() {
        mark.setMarkId(id);
        assertThat(mark.getMarkId()).isEqualTo(id);

        mark.setEnrollmentId(id);
        assertThat(mark.getEnrollmentId()).isEqualTo(id);

        mark.setMark(5.5f);
        assertThat(mark.getMark()).isEqualTo(5.5f);

        mark.setWeighting(50);
        assertThat(mark.getWeighting()).isEqualTo(50);

        assertThat(mark.toString()).startsWith("Mark");
    }
}