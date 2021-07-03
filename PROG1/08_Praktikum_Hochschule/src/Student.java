/**
 * Klasse Student veerebt aus Person
 * 
 * @author Gabriele Pace
 *
 */
public class Student extends Person {

	private int anzahlCredits;

	/**
	 * Erzeugt ein Student mit:
	 * 
	 * @param name
	 * @param id
	 */
	public Student(String name, String id) {
		super(name, id);
	}

	/**
	 * Gibt die Anzahl Credits zurück.
	 * 
	 * @return anzahlCredits
	 */
	public int gibCredits() {
		return anzahlCredits;
	}

	/**
	 * Erhöht die Anzahl Credit mit dem Parameter.
	 * 
	 * @param credit
	 */
	public void erhoeheCredits(int credit) {
		anzahlCredits += credit;
	}
}
