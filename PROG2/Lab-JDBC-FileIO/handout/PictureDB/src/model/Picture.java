package model;

import java.net.URL;
import java.util.Date;

public class Picture {

    private int id;
    private URL url;
    private Date date;
    private String title;
    private String comment;
    private float longitude;
    private float latitude;
    private float altitude;


    public Picture(int id, URL url, Date date, String title, String comment, 
            float longitude, float latitude, float altitude) {
        super();
        this.id = id;
        this.url = url;
        this.date = date;
        this.title = title;
        this.comment = comment;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Picture(URL url, Date date, String title, String comment, 
            float longitude, float latitude, float altitude) {
        this(-1, url, date, title, comment, longitude, latitude, altitude);
    }

    public Picture(URL url, String title) {
        this(-1, url, new Date(), title, "", 0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Picture");
        sb.append("\n\t id: "+id);
        sb.append("\n\t url: "+url);
        sb.append("\n\t title: "+title);
        sb.append("\n\t comment: "+comment);
        sb.append("\n\t longitude: "+longitude);
        sb.append("\n\t latitude: "+latitude);
        sb.append("\n\t altitude: "+altitude);
        return sb.toString();
    }
}
