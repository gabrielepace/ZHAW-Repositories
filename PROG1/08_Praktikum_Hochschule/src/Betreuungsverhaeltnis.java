import java.util.ArrayList;
import java.util.Random;

/**
 * Diese Klasse verwaltet einen Dozenten und die von ihm betreuten Studenten.
 * 
 * @author Marc Rennhard
 */
public class Betreuungsverhaeltnis {
	private Dozent dozent;
	private ArrayList<Student> studenten;

	/**
	 * Erzeuge ein Betreuungsverhaeltnis.
	 * 
	 * @param dozent Der Dozent
	 */
	public Betreuungsverhaeltnis(Dozent dozent) {
		this.dozent = dozent;
		studenten = new ArrayList<Student>();
	}

	/**
	 * Fuege einen Studenten hinzu.
	 * 
	 * @param student Der Student
	 */
	public void studentZufuegen(Student student) {
		studenten.add(student);
	}

	/**
	 * Verteilt ein gewisse Anzahl Credits zufällig unter den Studenten, total
	 * werden Anzahl Studenten * 40 Credits verteilt.
	 */
	public void verteileCredits() {
		Random random = new Random();

		for (int i = 0; i < (10 * studenten.size()); i++) {
			studenten.get(random.nextInt(studenten.size())).erhoeheCredits(4);
		}
	}

	/**
	 * Gibt die Informationen dieses Betreuungsverhaeltnisses aus.
	 */
	public void ausgeben() {
		System.out.println("Dozent " + dozent.gibInfo() + ", Buero " + dozent.gibBuero() + ", Telefon: "
				+ dozent.gibTelefonnummer());
		System.out.println("betreut " + studenten.size() + " Studenten:");
		for (Student student : studenten) {
			System.out.println(student.gibInfo() + ", " + student.gibCredits() + " credits");
		}
	}
}
