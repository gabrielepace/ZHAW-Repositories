package aufgabe3;

public abstract class KoffeinGetraenk {

	public abstract void bereiteZu();

	protected void kocheWasser() {
		System.out.println("Wasser wird gekocht.");
	}

	protected void giesseInTasse() {
		System.out.println("In Tasse wird gegossen.");
	}

}
