package ch.zhaw.project.easycts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ModuleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleGroupId;
    private String name;

    public ModuleGroup() {}

    public ModuleGroup(String name) {
        this.name = name;
    }

    public Long getModuleGroupId() {
        return moduleGroupId;
    }

    public void setModuleGroupId(Long moduleGroupId) {
        this.moduleGroupId = moduleGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
