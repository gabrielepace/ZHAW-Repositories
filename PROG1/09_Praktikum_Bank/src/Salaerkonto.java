/**
 * @author Gabriele Pace (pacegab1)
 *
 */

public class Salaerkonto extends Bankkonto {

	private double ueberzugslimite;
	private double kontostand = super.getKontostand();

	public Salaerkonto(String inhaber, double kontostand, double ueberzugslimite) {
		super(inhaber, kontostand);
		this.ueberzugslimite = ueberzugslimite;
	}

	protected double getUeberzugslimite() {
		return ueberzugslimite;
	}

	protected void setUeberzugslimite(double ueberzugslimite) {
		this.ueberzugslimite = ueberzugslimite;
	}

	@Override
	protected void geldEinbezahlen(double geldEinzahlen) {
		kontostand += geldEinzahlen;
		System.out.println("Ihr/e neue Kontostand: " + kontostand);
	}

	@Override
	protected void geldAbheben(double geldAbheben) {
		if (kontostand >= (-ueberzugslimite)) {
			kontostand -= geldAbheben;
		} else if (kontostand <= ueberzugslimite) {
			System.out.println("Nicht möglich mehr als " + ueberzugslimite + " zu abheben!");
		}
		System.out.println("Ihr/e Kontostand: " + kontostand);
	}

	@Override
	public String toString() {
		return super.toString() + ", Ueberzugslimite: " + ueberzugslimite;
	}

}
