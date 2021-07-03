/**
 * Zaehlt die Anzahl Vorkommnisse von Woertern ueber mehrere Zeichenketten.
 * Es lassen sich eine beliebige Anzahl an Zeichenketten uebergeben. Die
 * Statistik wird fortlaufend gefuehrt. Die Wortzaehlung laesst sich jederzeit
 * ausgeben. Die Satzeichen . , ? ! " : ; werden entfernt. Alle Buchstaben
 * werden in Kleinbuchstaben umgewandelt um beispielsweise das Wort 'die'
 * inmitten eines Satzes und das Wort 'Die' am Anfang eines Satzes als gleiches
 * Wort werten zu koennen.
 * 
 * @version 1.0
 * @author Gabriele Pace (pacegab1)
 */
import java.util.*;
import java.util.Map.*;

public class Worthaeufigkeitsanalyse {
	
	private Map<String, Integer> words;
	
	public Worthaeufigkeitsanalyse() {
		words = new HashMap<>();
	}
	
	public void verarbeiteText(String text) {
		String[] input = text.split(" ");
		
		for(String word : input) {
			word = word.toLowerCase();
			word = word.replaceAll("[.,?!:\"]", "");
			Integer counter = words.get(word);
			
			if(counter != null) {
				words.put(word, ++counter);
			}else {
				words.put(word, 1);
			}
		}
	}
	
	public void druckeStatistik() {
		for(Entry<String, Integer> word : words.entrySet()) {
			System.out.println(word.getKey() + " " + word.getValue());
		}
	}
}
