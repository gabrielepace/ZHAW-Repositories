/**
 * Klasse Dozent veerebt aus Person.
 * 
 * @author Gabriele Pace
 *
 */
public class Dozent extends Person {

	private String Buronummer;
	private String Telefonnummer;

	/**
	 * Erzeugt ein Dozent mit:
	 * 
	 * @param name
	 * @param id
	 * @param Buronummer
	 * @param Telefonnummer
	 */
	public Dozent(String name, String id, String Buronummer, String Telefonnummer) {
		super(name, id);
		this.Buronummer = Buronummer;
		this.Telefonnummer = Telefonnummer;
	}

	/**
	 * Gibt den Buronummer zurück.
	 * 
	 * @return Buronummer
	 */
	public String gibBuero() {
		return Buronummer;
	}

	/**
	 * Gibt den Telefonnummer zurück.
	 * 
	 * @return Telefonnummer
	 */
	public String gibTelefonnummer() {
		return Telefonnummer;
	}
}
