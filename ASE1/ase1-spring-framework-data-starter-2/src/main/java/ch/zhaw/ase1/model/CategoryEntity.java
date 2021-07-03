package ch.zhaw.ase1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Is not in Domain Model, but necessary as a Category is identified with an id

    public CategoryEntity(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public CategoryEntity(int id) {
        this.id = id;
    }

    public CategoryEntity() {
    }

    @Column
    public String description;

    @Column
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
