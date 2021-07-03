package ch.zhaw.project.easycts.DTO;

import ch.zhaw.project.easycts.model.Mark;

import java.util.List;

public class EnrollmentDTO {
    private String name;
    private List<Mark> marks;
    private int ects;
    private String moduleGroupName;
    private long enrollmentId;
    private boolean dispensed;

    public EnrollmentDTO(String name, List<Mark> marks, int ects, String moduleGroupName, long enrollmentId, boolean dispensed) {
        this.name = name;
        this.marks = marks;
        this.ects = ects;
        this.moduleGroupName = moduleGroupName;
        this.enrollmentId = enrollmentId;
        this.dispensed = dispensed;
    }

    @Override
    public String toString() {
        return "EnrollmentDTO{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                ", ects=" + ects +
                ", moduleGroupName='" + moduleGroupName + '\'' +
                ", enrollmentId=" + enrollmentId +
                ", dispensed=" + dispensed +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getModuleGroupName() {
        return moduleGroupName;
    }

    public void setModuleGroupName(String moduleGroupName) {
        this.moduleGroupName = moduleGroupName;
    }

    public boolean isDispensed() {
        return dispensed;
    }

    public void setDispensed(boolean dispensed) {
        this.dispensed = dispensed;
    }
}
