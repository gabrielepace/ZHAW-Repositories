package ch.zhaw.ase1.domain;

public class AuctionItem {
    private String Description;
    private String Picture;
    private String Title;

    public AuctionItem(String description, String picture, String title) {
        Description = description;
        Picture = picture;
        Title = title;
    }

    public AuctionItem() {
        this.Description = "Test Auction Item Description";
        this.Picture = "Test Auction Item Picture";
        this.Title = "Test Auction Item Titel";

    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
