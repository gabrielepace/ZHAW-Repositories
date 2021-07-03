
/**
 * Diese Klasse modelliert Räume in der Welt von Zuul.
 * 
 * Ein "Raum" repräsentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Räumen über Ausgänge verbunden.
 * Mögliche Ausgänge liegen im Norden, Osten, Süden und Westen.
 * Für jede Richtung hält ein Raum eine Referenz auf den 
 * benachbarten Raum.
 * 
 * @author  Michael Kölling und David J. Barnes
 * @version 31.07.2011
 */
import java.util.HashMap;

public class Raum {
	private String beschreibung;

	private HashMap<String, Raum> ausgaenge;

	/**
	 * Erzeuge einen Raum mit einer Beschreibung. Ein Raum hat anfangs keine
	 * Ausgänge.
	 * 
	 * @param beschreibung enthält eine Beschreibung in der Form "in einer Küche"
	 *                     oder "auf einem Sportplatz".
	 */
	public Raum(String beschreibung) {
		this.beschreibung = beschreibung;
		ausgaenge = new HashMap<>();
	}

	public void setzeAusgaenge(String richtung, Raum raum) {
		ausgaenge.put(richtung, raum);
	}

	public Raum gibAusgang(String richtung) {
		return ausgaenge.get(richtung);
	}
	
	/**
	* Liefere eine Beschreibung der Ausgänge dieses Raumes, bespielsweise
	* "Ausgänge: Labor".
	*
	* @return eine Beschreibung der verfügbaren Ausgänge
	*/
	public String gibAusgaengeAlsString() {
		System.out.println("Ausgänge: ");
		return ausgaenge.toString();
	}

	/**
	 * Definiere die Ausgänge dieses Raums. Jede Richtung führt entweder in einen
	 * anderen Raum oder ist 'null' (kein Ausgang).
	 * 
	 * @param draussen  Draussen.
	 * @param hoersaal  Der Hörsaal bzw. Vorlesungshörsaal.
	 * @param cafeteria Die Cafeteria.
	 * @param labor     Das Labor.
	 * @param buero     Der Büro.
	 */

	/**
	 * @return die Beschreibung dieses Raums.
	 */
	public String gibLangeBeschreibung() {
		return beschreibung;
	}
	
	public void printExits(){
		System.out.println(ausgaenge);
	}
}
