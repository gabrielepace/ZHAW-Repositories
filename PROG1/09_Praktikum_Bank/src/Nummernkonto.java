/**
 * @author Gabriele Pace (pacegab1)
 *
 */

public class Nummernkonto extends Bankkonto {

	private static int counterNummer = 999;
	private int nummer = 0;

	public Nummernkonto(String inhaber, double kontostand) {
		super(inhaber, kontostand);
		nummer = counterNummer++;
	}
	
	@Override
	protected String getInhaber() {
		return String.valueOf(nummer);
	}

	@Override
	public String toString() {
		return "Inhaber: " + getInhaber() + ", Kontostand CHF: " + getKontostand();

	}

}
