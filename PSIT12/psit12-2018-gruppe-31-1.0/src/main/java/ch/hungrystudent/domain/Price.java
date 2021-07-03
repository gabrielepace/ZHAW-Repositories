package ch.hungrystudent.domain;

/**
 * Price enumeration
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public enum Price {
	CHEAP("Billig (< 10)"), MEDIUM("Mittel (10-15)"), EXPENSIVE("Teuer (> 15)");

	private final String value;

	Price(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
