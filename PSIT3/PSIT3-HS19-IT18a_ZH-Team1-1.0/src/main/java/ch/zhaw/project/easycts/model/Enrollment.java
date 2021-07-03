package ch.zhaw.project.easycts.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Enrollment.findBySemester", query = "SELECT e FROM Enrollment e WHERE e.semester = ?1")
@NamedQuery(name = "Enrollment.findByUserId", query = "SELECT e FROM Enrollment e WHERE e.creator.userId = ?1")
@Table(name = "Enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long enrollmentId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User creator;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "moduleId")
    private Module module;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollmentId")
    private List<Mark> marks;

    private int semester;
    private boolean dispensed;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isDispensed() {
        return dispensed;
    }

    public void setDispensed(boolean dispensed) {
        this.dispensed = dispensed;
    }

    public long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

}
