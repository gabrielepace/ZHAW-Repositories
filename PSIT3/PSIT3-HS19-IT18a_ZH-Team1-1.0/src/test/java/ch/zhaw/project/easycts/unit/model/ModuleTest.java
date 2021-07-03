package ch.zhaw.project.easycts.unit.model;

import ch.zhaw.project.easycts.model.Module;
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
public class ModuleTest {

    private Module module;
    private ModuleGroup mg;
    private final long id = 1L;

    @Before
    public void init() {
        module = new Module();
        mg = new ModuleGroup("MG1");
    }

    @Test
    public void testModuleGetterAndSetter() {
        module.setModuleId(id);
        assertThat(module.getModuleId()).isEqualTo(id);

        module.setModuleGroup(mg);
        assertThat(module.getModuleGroup()).isEqualTo(mg);

        module.setName("name");
        assertThat(module.getName()).isEqualTo("name");

        module.setEcts(4);
        assertThat(module.getEcts()).isEqualTo(4);

        module.setContraction("xxx");
        assertThat(module.getContraction()).isEqualTo("xxx");

        module.setDescription("desc");
        assertThat(module.getDescription()).isEqualTo("desc");
    }

}