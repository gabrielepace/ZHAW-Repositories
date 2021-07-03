package ch.hungrystudent.domain;

/**
 * Kitchen enumeration
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public enum Kitchen {
	FASTFOOD("Fast Food"), ASIAN("Asiatische Küche"), BARS("Bars"), CAFE("Cafés"), EUROPEAN("Europäische Küche"),
	SWISS("Schweizerische Küche");

	private final String value;

	Kitchen(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
