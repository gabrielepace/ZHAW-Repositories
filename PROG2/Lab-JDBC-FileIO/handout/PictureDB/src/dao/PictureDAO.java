package dao;

import java.util.*;

import model.Picture;

public interface PictureDAO {
    public void insert(Picture item);
    public void update(Picture item);
    public void delete(Picture item);   
    public int count();    
    public Picture findById(int id);
    public Collection<Picture> findAll();
    public Collection<Picture>findByPosition(float longitude, float latitude, float deviation);
}
