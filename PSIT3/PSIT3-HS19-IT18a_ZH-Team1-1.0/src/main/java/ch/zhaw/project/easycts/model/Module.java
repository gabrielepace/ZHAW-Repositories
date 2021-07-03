package ch.zhaw.project.easycts.model;

import javax.persistence.*;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moduleId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "moduleGroupId")
    private ModuleGroup moduleGroup;

    private String name;
    private int ects;
    private String contraction;
    private String description;


    public Module() {}

    public Module(String name) {
        this.name = name;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public ModuleGroup getModuleGroup() {
        return moduleGroup;
    }

    public void setModuleGroup(ModuleGroup moduleGroup) {
        this.moduleGroup = moduleGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getContraction() {
        return contraction;
    }

    public void setContraction(String contraction) {
        this.contraction = contraction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
