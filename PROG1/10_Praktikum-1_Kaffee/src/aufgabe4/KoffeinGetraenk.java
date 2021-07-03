package aufgabe4;

public abstract class KoffeinGetraenk {

	public final void bereiteZu() {
		kocheWasser();
		braue();
		giesseInTasse();
		fuegeZutatenHinzu();
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

}
