package aufgabe6;

/**
 * Diese Klasse bietet die Funktionalitaet, um einen Tee zu kochen.
 * 
 * @author tebe
 */
public class Tee extends KoffeinGetraenk {

	/**
	 * Bereitet einen Tee zu.
	 */

	@Override
	protected void kocheWasser() {
		System.out.println("Wasser für den Tee wird gekocht.");
	}
	
	@Override
	protected void braue() {
		System.out.println("Teebeutel wird gebraucht.");
	}

	@Override
	protected void giesseInTasse() {
		
		System.out.print(getClass().getSimpleName() + " ");
		super.giesseInTasse();
	}

	@Override
	protected void fuegeZutatenHinzu() {
		System.out.println("Zitrone wird hinzugefügt.");
	}

	@Override
	public void trinke() {
		System.out.println("Ich trinke einen " + getClass().getSimpleName());
		
	}
}
