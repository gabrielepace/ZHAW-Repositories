package aufgabe6;

public abstract class KoffeinGetraenk implements Trinkbar {

	public final void bereiteZu() {
		kocheWasser();
		braue();
		giesseInTasse();
		fuegeZutatenHinzu();
		kraftstoffe(); // Diese Methode ist gültig für die Klasse Tee & Kaffee
	}

	protected void kocheWasser() {
		System.out.println("Wasser wird gekocht.");
	}

	protected void giesseInTasse() {
		System.out.println("Tasse wird gegossen.");
	}
	
	protected void braue() {
		System.out.println("Wird gebraut.");
	}
	
	protected void fuegeZutatenHinzu() {
		System.out.println("Zutaten werden hinzugefügt.");
	}
	
	public void kraftstoffe() {
		System.out.println("Kraftstoffe");
	}

}
