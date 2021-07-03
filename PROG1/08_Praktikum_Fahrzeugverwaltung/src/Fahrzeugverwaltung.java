import java.util.ArrayList;

/**
 * Diese Klasse verwaltet die Fahrzeuge.
 *
 * @author Marc Rennhard
 */
public class Fahrzeugverwaltung {
	private ArrayList<Fahrzeug> fahrzeuge;

	/**
	 * Konstruktor. Erzeugt eine Fahrzeugverwaltung.
	 */
	public Fahrzeugverwaltung() {
		fahrzeuge = new ArrayList<Fahrzeug>();
	}

	/**
	 * Erzeugt ein Fahrrad.
	 *
	 * @param modell     Das Modell
	 * @param preis      Der Preis
	 * @param stueckzahl Die verfuegbare Stueckzahl
	 * @param gaenge     Die Anzahl gaenge
	 */
	public void erzeugeFahrrad(String modell, int preis, int stueckzahl, int gaenge) {
		Fahrrad fahrrad = new Fahrrad(modell, preis, stueckzahl, gaenge);
		fahrzeuge.add(fahrrad);
	}

	/**
	 * Erzeugt ein Motorrad.
	 *
	 * @param modell     Das Modell
	 * @param preis      Der Preis
	 * @param stueckzahl Die verfuegbare Stueckzahl
	 * @param leistung   Die Leistung
	 * @param abs        Ob das Motorrad ABS hat
	 */
	public void erzeugeMotorrad(String modell, int preis, int stueckzahl, int leistung, boolean abs) {
		Motorrad motorrad = new Motorrad(modell, preis, stueckzahl, leistung, abs);
		fahrzeuge.add(motorrad);
	}

	/**
	 * Erzeugt ein Auto.
	 *
	 * @param modell         Das Modell
	 * @param preis          Der Preis
	 * @param stueckzahl     Die verfuegbare Stueckzahl
	 * @param leistung       Die Leistung
	 * @param plaetze        Die Anzahl Sitzplaetze
	 * @param vierradantrieb Ob das Auto Vierradantrieb hat
	 */
	public void erzeugeAuto(String modell, int preis, int stueckzahl, int leistung, int plaetze,
			boolean vierradantrieb) {
		Auto auto = new Auto(modell, preis, stueckzahl, leistung, plaetze, vierradantrieb);
		fahrzeuge.add(auto);
	}

	/**
	 * Kauft ein Fahrrad in der gewuenschten Stueckzahl.
	 *
	 * @param modell     Das gewuenschte Modell
	 * @param stueckzahl Die gewuenschte Stueckzahl
	 * @param kunde      Der Kunde
	 * @return Informationen ueber das Ergebnis des Kaufs
	 */
	public void kaufeFahrzeug(String modell, int stueckzahl, Kunde kunde) {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			if (fahrzeug.gibModell().equals(modell)) {
				String kaufergebnis = fahrzeug.kaufen(stueckzahl, kunde);
				System.out.println(kaufergebnis);
				return;
			}
		}
		System.out.println("Das Modell " + modell + " konnte nicht gefunden werden");
	}

	/**
	 * Gibt Informationen aller Fahrzeuge aus
	 */
	public void ausgeben() {
		for (Fahrzeug fahrzeug : fahrzeuge) {
			System.out.println(fahrzeug.gibInfo());
		}
	}
}