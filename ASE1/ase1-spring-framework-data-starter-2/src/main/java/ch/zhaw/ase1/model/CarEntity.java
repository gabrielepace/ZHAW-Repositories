package ch.zhaw.ase1.model;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "CarEntity.getAllCars", query = "select c from CarEntity c"),
                @NamedQuery(name = "CarEntity.getById", query = "select c from CarEntity c where c.id = :id")
        }
)
public class CarEntity implements Serializable {

	private static final long serialVersionUID = 3469445331977914511L;

	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(name = "model_identification", nullable = false)
    private String model;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER /* LAZY */, mappedBy = "car")
    private List<WheelEntity> wheels;


    @ManyToMany(mappedBy = "cars")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PersonEntity> owners;

    public CarEntity(String model, List<WheelEntity> wheels) {
        this.model = model;
        this.wheels = wheels;
    	for (WheelEntity wheel : this.wheels) {
        	if (wheel.getCar() == null) wheel.setCar(this);
     	}

    }

    public CarEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<WheelEntity> getWheels() {
        return wheels;
    }

    public List<PersonEntity> getOwners() {
        return owners;
    }

    public  void addOwner(PersonEntity p) {
    	if (this.owners == null) {
    		this.owners = new ArrayList<PersonEntity>();
    	}
        this.owners.add(p);
    }



    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", wheels=" + wheels +
                ", owners=" + owners +
                '}';
    }
}
