package ch.zhaw.ase1.domain;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.ArrayList;

@Component("auction")
public class Auction {


    @Inject
    private Category category;

    @Inject
    private AuctionItem auctionItem;

    @Resource
    private ArrayList<Bid> bidArrayList;

    private String EndDateTime;
    private int FixedPrice;
    private int MinBidIncrement;
    private String StartDateTime;
    private int StartingPrice;
    private Boolean Status;


//    public Auction(String endDateTime, int fixedPrice, int minBidIncrement, String startDateTime, int startingPrice,
//                   Boolean status, Category category, AuctionItem auctionItem, ArrayList<Bid> bidArrayList) {
//        EndDateTime = endDateTime;
//        FixedPrice = fixedPrice;
//        MinBidIncrement = minBidIncrement;
//        StartDateTime = startDateTime;
//        StartingPrice = startingPrice;
//        Status = status;
//        this.category = category;
//        this.auctionItem = auctionItem;
//        this.bidArrayList = bidArrayList;
//    }


    public Auction() {
        System.out.println("Called Auction Constructor");
    }

    public String getEndDateTime() {
        return EndDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        EndDateTime = endDateTime;
    }

    public int getFixedPrice() {
        return FixedPrice;
    }

    public void setFixedPrice(int fixedPrice) {
        FixedPrice = fixedPrice;
    }

    public int getMinBidIncrement() {
        return MinBidIncrement;
    }

    public void setMinBidIncrement(int minBidIncrement) {
        MinBidIncrement = minBidIncrement;
    }

    public String getStartDateTime() {
        return StartDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        StartDateTime = startDateTime;
    }

    public int getStartingPrice() {
        return StartingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        StartingPrice = startingPrice;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public void setAuctionItem(AuctionItem auctionItem) {
        this.auctionItem = auctionItem;
    }

    public ArrayList<Bid> getBidArray() {
        return bidArrayList;
    }

    public void setBidArray(ArrayList<Bid> bidArray) {
        this.bidArrayList = bidArray;
    }
}
