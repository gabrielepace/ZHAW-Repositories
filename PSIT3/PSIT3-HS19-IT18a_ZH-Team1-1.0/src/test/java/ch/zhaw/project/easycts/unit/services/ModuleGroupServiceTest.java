package ch.zhaw.project.easycts.unit.services;

import ch.zhaw.project.easycts.model.ModuleGroup;
import ch.zhaw.project.easycts.repositories.ModuleGroupRepository;
import ch.zhaw.project.easycts.services.ModuleGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleGroupServiceTest {

    @Autowired
    private ModuleGroupService moduleGroupS;
    @MockBean
    private ModuleGroupRepository moduleGroupRep;
    @MockBean
    private ModuleGroup mg;

    private final String name = "name";

    @Test
    public void testModuleGroupService() {
        reset(moduleGroupRep);
        when(moduleGroupRep.findByName(name)).thenReturn(null);
        moduleGroupS.getModuleGroupByName(name);
        verify(moduleGroupRep, times(1)).findByName(name);

        when(moduleGroupRep.save(mg)).thenReturn(mg);
        moduleGroupS.saveModuleGroup(mg);
        verify(moduleGroupRep, times(1)).save(mg);
    }
}