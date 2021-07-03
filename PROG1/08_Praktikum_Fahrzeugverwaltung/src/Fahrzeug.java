import java.util.ArrayList;

public class Fahrzeug {
	protected String modell;
	protected int preis;
	protected int stueckzahl;
	
	private ArrayList<Verkauf> verkaeufe;

	public Fahrzeug(String modell, int preis, int stueckzahl) {
		this.modell = modell;
		this.preis = preis;
		this.stueckzahl = stueckzahl;
		verkaeufe = new ArrayList<Verkauf>();
	}

	/**
	 * Kaufe ein Fahrzug in der gewuenschten Stueckzahl.
	 *
	 * @param stueckzahlKaufen Die gewuenschte Stueckzahl
	 * @param kunde            Der Kunde
	 * @return Informationen ueber das Ergebnis des Kaufs
	 */
	public String kaufen(int stueckzahlKaufen, Kunde kunde) {
		if (stueckzahlKaufen <= stueckzahl) {
			stueckzahl -= stueckzahlKaufen;
			Verkauf verkauf = new Verkauf(kunde, stueckzahlKaufen, stueckzahlKaufen * preis);
			verkaeufe.add(verkauf);
			return kunde.gibInfo() + " hat " + stueckzahlKaufen + " Stueck des Modells " + modell + " zu insgesamt CHF "
					+ (stueckzahlKaufen * preis) + " gekauft";

		} else {
			return "Es hat leider nur noch " + stueckzahl + " Stueck des Modells " + modell + " an Lager";
		}
	}

	/**
	 * Liefert das Modell des Autos.
	 *
	 * @return Das Modell
	 */
	public String gibModell() {
		return modell;
	}

	/**
	 * Setzt den Preis des Autos.
	 *
	 * @param preis Der Preis
	 */
	public void setzePreis(int preis) {
		if (preis > 0) {
			this.preis = preis;
		}
	}

	/**
	 * Setze die verfuegbare Stueckzahl des Autos.
	 *
	 * @param stueckzahl Die Stueckzahl
	 */
	public void setzeStueckzahl(int stueckzahl) {
		if (stueckzahl >= 0) {
			this.stueckzahl = stueckzahl;
		}
	}

	/**
	 * Gibt Informationen des Autos zurueck.
	 *
	 * @return Informationen des Verkaufs
	 */
	public String gibInfo() {
		String resultat = "Modell " + modell + ", " + stueckzahl + " Fahrzeuge zu je CHF " + preis + " an Lager.\n";
		resultat += "Bereits erfolgte Verkaeufe:\n";
		for (Verkauf verkauf : verkaeufe) {
			resultat += verkauf.gibInfo() + "\n";
		}
		return resultat;
	}
}
