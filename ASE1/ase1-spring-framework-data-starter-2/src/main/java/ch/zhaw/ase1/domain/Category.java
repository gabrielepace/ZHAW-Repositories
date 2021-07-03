package ch.zhaw.ase1.domain;

public class Category {
    private String Description;
    private String Name;

    public Category(String description, String name) {
        Description = description;
        Name = name;
    }

    public Category() {
        this.Description = "Test Category";
        this.Name = "Test Name";
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
