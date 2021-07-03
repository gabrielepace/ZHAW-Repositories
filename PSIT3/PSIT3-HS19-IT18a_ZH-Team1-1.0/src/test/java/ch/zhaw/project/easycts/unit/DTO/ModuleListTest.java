package ch.zhaw.project.easycts.unit.DTO;

import ch.zhaw.project.easycts.DTO.ModuleList;
import ch.zhaw.project.easycts.DTO.SemesterDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleListTest {
    private ModuleList moduleList;
    private List<SemesterDTO> semesters;
    private Set<String> mgs;

    @Before
    public void init() {
        moduleList = new ModuleList(null, null);

        semesters = new ArrayList<>();
        semesters.add(new SemesterDTO(1));

        mgs = new HashSet<>();
        mgs.add("MG1");
    }

    @Test
    public void testModuleListGetterAndSetter() {
        moduleList.setModuleGroupList(mgs);
        assertThat(moduleList.getModuleGroupList()).isEqualTo(mgs);

        moduleList.setSemesterDTOList(semesters);
        assertThat(moduleList.getSemesterDTOList()).isEqualTo(semesters);

        assertThat(moduleList.toString()).startsWith("ModuleList");
    }
}