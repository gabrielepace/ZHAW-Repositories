package ch.zhaw.project.easycts.DTO;


import java.util.ArrayList;
import java.util.List;

public class SemesterDTO {
    private int semesterId;
    private List<EnrollmentDTO> enrollments;

    public SemesterDTO(int semesterId) {
        this.semesterId = semesterId;
        enrollments = new ArrayList<>();
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public void addEnrollment(EnrollmentDTO e) {
        enrollments.add(e);
    }

    public List<EnrollmentDTO> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<EnrollmentDTO> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "SemesterDTO{" +
                "semesterId=" + semesterId +
                ", enrollments=" + enrollments +
                '}';
    }
}
