package aufgabe4;

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
		System.out.println("Teebeutel wird getaucht.");
	}

	@Override
	protected void giesseInTasse() {
		System.out.println("Der Tee Tasse wird gegossen.");
	}

	@Override
	protected void fuegeZutatenHinzu() {
		System.out.println("Zitrone wird hinzugefügt.");
	}
}
