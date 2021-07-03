/**
 * Klasse Person.
 * 
 * @author Gabriele Pace (pacegab1)
 *
 */
public class Person {

	private String name;
	private String id;

	/**
	 * Erzeugt eine Person mit:
	 * 
	 * @param name
	 * @param id
	 */
	public Person(String name, String id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Gibt als Info den Namen und den ID der Person zurück.
	 * 
	 * @return name, id
	 */
	protected String gibInfo() {
		return "Name: " + name + " ID: " + id;
	}
}
