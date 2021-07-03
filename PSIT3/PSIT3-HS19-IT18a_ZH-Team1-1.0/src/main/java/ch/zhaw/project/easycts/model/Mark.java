package ch.zhaw.project.easycts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long markId;
    private Long enrollmentId;
    private float mark;
    private int weighting;

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public int getWeighting() {
        return weighting;
    }

    public void setWeighting(int weighting) {
        this.weighting = weighting;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "markId=" + markId +
                ", enrollmentId=" + enrollmentId +
                ", mark=" + mark +
                ", weighting=" + weighting +
                '}';
    }
}
