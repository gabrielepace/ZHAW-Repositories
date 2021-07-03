package ch.zhaw.ase1.model;



import javax.persistence.*;

@Entity
public class WheelEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;


    public CarEntity getCar() {
    	return this.car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @ManyToOne()
    private CarEntity car;

    @Override
    public String toString() {
        return "WheelEntity{" +
                "id=" + id +
                '}';
    }
}
