/**
 * Die Klasse Buch repraesentiert ein Buch und beinhaltet 
 * Buchtitel, ISBN-Nummer (ISBN-13, z.B. 978-3868949070) 
 * und den Lagerbestand. 
 * @author Marc Rennhard
 */
public class Buch {
	public String titel;
	private String isbn;
	// Der aktuelle Bestand
	private int bestand;
	
	/**
	 * Erzeugt ein Buch mit den spezifizierten Werten fuer 
	 * Titel und ISBN-Nummer und setzt den Bestand auf 0.
	 * @param buchTitel Der Buchtitel.
	 */
	public Buch(String buchTitel, String buchISBN) {
		titel = buchTitel;
		isbn = buchISBN;
	}
	
	/**
	 * Liefert den aktuellen Bestand.
	 * @return Der Bestand.
	 */
	public int bestand() {
		return bestand;
	}
	
	/**
	 * Veraendert den Bestand um den angegebenen Wert.
	 * @param veraenderung Die Veraenderung des Bestands.
	 */
	public void veraendereBestand(int veraenderung) {
		bestand += veraenderung;
       }
	
	/**
	 * Gibt die Informationen eines Buches aus.
	 */
	public void output() {
		System.out.println("Titel: " + titel);
		System.out.println("ISBN-13: " + isbn);
		System.out.println("Bestand: " + bestand);
	}
}
