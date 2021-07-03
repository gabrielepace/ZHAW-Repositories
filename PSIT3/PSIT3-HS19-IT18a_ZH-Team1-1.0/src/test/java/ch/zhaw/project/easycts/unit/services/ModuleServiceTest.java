package ch.zhaw.project.easycts.unit.services;

import ch.zhaw.project.easycts.model.Module;
import ch.zhaw.project.easycts.repositories.ModuleRepository;
import ch.zhaw.project.easycts.services.ModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleServiceTest {

    @Autowired
    private ModuleService moduleS;
    @MockBean
    private ModuleRepository moduleRep;

    @Test
    public void testModuleService() {
        reset(moduleRep);
        moduleS.saveModule(new Module());
        verify(moduleRep, times(1)).save(any());
    }
}