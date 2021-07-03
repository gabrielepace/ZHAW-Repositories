package ch.zhaw.ase1.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = -7376032172460507651L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Address adress;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "OWNER_CAR"
    )
    private List<CarEntity> cars;

    public PersonEntity(String name, Address adress, List<CarEntity> cars) {
        this.name = name;
        this.adress = adress;
        this.cars = cars;
        if (cars != null) {
            for (CarEntity car : cars) {
            	car.addOwner(this);
            }
        }
    }

    public PersonEntity() {
    }

    public PersonEntity(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", adress=" + adress +
                ", name='" + name + '\'' +
                '}';
    }

    public List<CarEntity> getCars() {
    	return this.cars;
    }

    public Address getAddress() {
    	return this.adress;
    }

    public Integer getId() {
    	return this.id;
    }
}
