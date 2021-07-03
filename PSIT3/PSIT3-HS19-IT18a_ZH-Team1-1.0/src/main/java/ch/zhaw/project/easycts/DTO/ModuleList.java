package ch.zhaw.project.easycts.DTO;

import java.util.List;
import java.util.Set;

public class ModuleList {
    private List<SemesterDTO> semesterDTOList;
    private Set<String> moduleGroupList;

    public ModuleList(List<SemesterDTO> semesterDTOList, Set<String> moduleGroupList) {
        this.semesterDTOList = semesterDTOList;
        this.moduleGroupList = moduleGroupList;
    }

    public List<SemesterDTO> getSemesterDTOList() {
        return semesterDTOList;
    }

    public void setSemesterDTOList(List<SemesterDTO> semesterDTOList) {
        this.semesterDTOList = semesterDTOList;
    }

    public Set<String> getModuleGroupList() {
        return moduleGroupList;
    }

    public void setModuleGroupList(Set<String> moduleGroupList) {
        this.moduleGroupList = moduleGroupList;
    }

    @Override
    public String toString() {
        return "ModuleList{" +
                "semesterDTOList=" + semesterDTOList +
                ", moduleGroupList=" + moduleGroupList +
                '}';
    }
}
