package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.ModuleGroup;
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
public class ModuleGroupTest {

    private ModuleGroup mg;
    private final long id = 1L;

    @Before
    public void init() {
        mg = new ModuleGroup("MG1");
    }

    @Test
    public void testModuleGroupGetterAndSetter() {
        mg = new ModuleGroup();

        mg.setModuleGroupId(id);
        assertThat(mg.getModuleGroupId()).isEqualTo(id);

        mg.setName("name");
        assertThat(mg.getName()).isEqualTo("name");
    }

}