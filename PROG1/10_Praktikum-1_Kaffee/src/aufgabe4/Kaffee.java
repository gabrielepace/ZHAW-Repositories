package aufgabe4;

/**
 * Diese Klasse bietet die Funktionalitaet, um einen Kaffee zu kochen.
 * 
 * @author tebe
 */
public class Kaffee extends KoffeinGetraenk {

	@Override
	protected void kocheWasser() {
		System.out.println("Wasser f�r Kaffee wird gekocht.");
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
		System.out.println("Zucker und Milch wird hinzugef�gt.");
	}
}
