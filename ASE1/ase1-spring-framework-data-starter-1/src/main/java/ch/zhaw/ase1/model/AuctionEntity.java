package ch.zhaw.ase1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Is not in Domain Model, but necessary as an Auction is identified with an id

    public AuctionEntity(int id, Date endDateTime, float fixedPrice, float minBidIncrement, Date startDateTime, float startingPrice, String status) {
        this.id = id;
        this.endDateTime = endDateTime;
        this.fixedPrice = fixedPrice;
        this.minBidIncrement = minBidIncrement;
        this.startDateTime = startDateTime;
        this.startingPrice = startingPrice;
        this.status = status;
    }

    public AuctionEntity(int id) {
        this.id = id;
    }

    public AuctionEntity() {
    }

    @Column
    private Date endDateTime;

    @Column
    private float fixedPrice;

    @Column
    private float minBidIncrement;

    @Column
    private Date startDateTime;

    @Column
    private float startingPrice;

    @Column
    private String status;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private AuctionItemEntity auctionItemEntity;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private CategoryEntity categoryEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public float getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(float fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public float getMinBidIncrement() {
        return minBidIncrement;
    }

    public void setMinBidIncrement(float minBidIncrement) {
        this.minBidIncrement = minBidIncrement;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AuctionItemEntity getAuctionItemEntity() {
        return auctionItemEntity;
    }

    public void setAuctionItemEntity(AuctionItemEntity auctionItemEntity) {
        this.auctionItemEntity = auctionItemEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    @Override
    public String toString() {
        return "AuctionEntity{" +
                "id=" + id +
                ", endDateTime=" + endDateTime +
                ", fixedPrice=" + fixedPrice +
                ", minBidIncrement=" + minBidIncrement +
                ", startDateTime=" + startDateTime +
                ", startingPrice=" + startingPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
