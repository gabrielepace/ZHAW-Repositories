package ch.zhaw.ase1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Is not in Domain Model, but necessary as a Bid is identified with an id

    public BidEntity(int id, float amount, String cancelExplanation, Date placedAtDateTime) {
        this.id = id;
        this.amount = amount;
        this.cancelExplanation = cancelExplanation;
        this.placedAtDateTime = placedAtDateTime;
    }

    public BidEntity(int id) {
        this.id = id;
    }

    public BidEntity() {
    }

    @Column
    private float amount;

    @Column
    private String cancelExplanation;

    @Column
    private Date placedAtDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCancelExplanation() {
        return cancelExplanation;
    }

    public void setCancelExplanation(String cancelExplanation) {
        this.cancelExplanation = cancelExplanation;
    }

    public Date getPlacedAtDateTime() {
        return placedAtDateTime;
    }

    public void setPlacedAtDateTime(Date placedAtDateTime) {
        this.placedAtDateTime = placedAtDateTime;
    }

    @Override
    public String toString() {
        return "BidEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", cancelExplanation='" + cancelExplanation + '\'' +
                ", placedAtDateTime=" + placedAtDateTime +
                '}';
    }
}
