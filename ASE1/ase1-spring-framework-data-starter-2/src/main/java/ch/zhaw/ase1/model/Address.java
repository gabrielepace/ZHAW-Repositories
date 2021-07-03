package ch.zhaw.ase1.model;



import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private int number;

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    public Address() { }

    @Override
    public String toString() {
        return "Adress{" +
                "street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
