package ch.zhaw.ase1.model;

import javax.persistence.*;

@Entity
public class AuctionItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Is not in Domain Model, but necessary as an Auction Item is identified with an id

    public AuctionItemEntity(int id, String description, String title, String picture) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.picture = picture;
    }

    public AuctionItemEntity(int id) {
        this.id = id;
    }

    public AuctionItemEntity() {
    }

    @Column
    private String description;

    @Column
    private String title;

    @Column
    private String picture;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "AuctionItemEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
