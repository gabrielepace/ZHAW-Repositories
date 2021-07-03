/**
 * @author Gabriele Pace (pacegab1)
 *
 */

public class Bankkonto {

	private String inhaber;
	private double kontostand;
	protected static final double MAX_BETRAG = 100000.0;

	public Bankkonto(String inhaber, double kontostand) {
		this.inhaber = inhaber;
		this.kontostand = kontostand;
	}

	protected void geldEinbezahlen(double geldEinzahlen) {
		if (getKontostand() <= MAX_BETRAG) {
			kontostand += geldEinzahlen;
		} else {
			System.out.println("Sie können nicht mehr als CHF " + MAX_BETRAG + " einzahlen!");
			geldEinbezahlen(geldEinzahlen);
		}
		System.out.println("Ihr/e neue Kontostand: " + kontostand);
	}

	protected void geldAbheben(double geldAbheben) {
		if (kontostand >= 0 || kontostand <= MAX_BETRAG) {
			kontostand -= geldAbheben;
		} else {
			System.out.println("Kein Geld zur Verfügung!");
		}
		System.out.println("Ihr/e Kontostand: " + kontostand);
	}

	protected void setInhaber(String inhaber) {
		this.inhaber = inhaber;
	}

	protected void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	protected String getInhaber() {
		return inhaber;
	}

	protected double getKontostand() {
		return kontostand;
	}

	public String toString() {
		return "Inhaber: " + getInhaber() + ", Kontostand CHF: " + getKontostand();
	}

}
