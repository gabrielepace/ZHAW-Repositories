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
public class Auction {

    @Id
    @GeneratedValue
    private Long id;

    private Date endDateTime;

    private Date startDateTime;

    private Boolean fixedPrice;

    private Double minBidIncrement;

    private Double startingPrice;

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Boolean getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Boolean fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public Double getMinBidIncrement() {
        return minBidIncrement;
    }

    public void setMinBidIncrement(Double minBidIncement) {
        this.minBidIncrement = minBidIncement;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
