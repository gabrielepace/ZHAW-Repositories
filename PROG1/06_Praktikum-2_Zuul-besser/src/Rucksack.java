import java.util.ArrayList;

public class Rucksack {
	
	private final ArrayList<Gegenstand> rucksack = new ArrayList<Gegenstand>();
	
	public ArrayList<Gegenstand> getRucksack() {
		return rucksack;
	}
	
	public int berechneGewicht(ArrayList<Gegenstand> rucksack) {
		int gewicht = 0;
		for (Gegenstand gegenstand : rucksack) {
			gewicht += gegenstand.gibGewicht();
		}
		return gewicht;
	}


}
