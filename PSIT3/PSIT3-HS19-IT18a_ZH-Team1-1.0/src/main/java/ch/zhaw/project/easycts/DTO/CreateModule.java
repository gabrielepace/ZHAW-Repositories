package ch.zhaw.project.easycts.DTO;

import ch.zhaw.project.easycts.model.Mark;

import java.util.List;

public class CreateModule {
    private Long enrollmentId;
    private String name;
    private int semester;
    private String modulegroup;
    private int ects;
    private boolean dispensed;
    private List<Mark> marks;
    private String token;

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getModulegroup() {
        return modulegroup;
    }

    public void setModulegroup(String modulegroup) {
        this.modulegroup = modulegroup;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public boolean isDispensed() {
        return dispensed;
    }

    public void setDispensed(boolean dispensed) {
        this.dispensed = dispensed;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreateModule{" +
                "enrollmentId=" + enrollmentId +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", modulegroup='" + modulegroup + '\'' +
                ", ects=" + ects +
                ", dispensed=" + dispensed +
                ", marks=" + marks +
                '}';
    }
}
