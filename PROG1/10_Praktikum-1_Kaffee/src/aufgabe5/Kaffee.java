package aufgabe5;

/**
 * Diese Klasse bietet die Funktionalitaet, um einen Kaffee zu kochen.
 * 
 * @author tebe
 */
public class Kaffee extends KoffeinGetraenk implements Trinkbar {

	@Override
	protected void kocheWasser() {
		System.out.println("Wasser für Kaffee wird gekocht.");
	}

	@Override
	protected void braue() {
		System.out.println("Kaffee Filter wird gebraut.");
	}

	@Override
	protected void giesseInTasse() {
		System.out.println("Kaffee Tasse wird gegossen.");
	}

	@Override
	protected void fuegeZutatenHinzu() {
		System.out.println("Zucker und Milch wird hinzugefügt.");
	}

	@Override
	public void trinke() {
		System.out.println("Ich trinke einen " + getClass().getSimpleName());
		
	}
}
