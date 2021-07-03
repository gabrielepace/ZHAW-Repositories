package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

/**
 * Model class for checkout
 */
@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    private Double amout;

    private String cancelExplanation;

    private Date placedAtDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmout() {
        return amout;
    }

    public void setAmout(Double amout) {
        this.amout = amout;
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
}
