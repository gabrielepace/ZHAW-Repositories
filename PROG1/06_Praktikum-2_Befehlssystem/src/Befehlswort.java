/**
 * Diese Klasse haelt eine Aufzaehlung der akzeptierten Befehlswoerter.
 * Mit ihrer Hilfe werden eingetippte Befehle erkannt.
 *
 * @author  tebe
 * @version 1.0
 */
public enum Befehlswort {
	UNBEKANNT("unbekannt"), GEHE("gehe"), HILFE("hilfe"), BEENDEN("beenden");
	
	private String befehl;
	
	private Befehlswort(String befehl) {
		this.befehl = befehl;
	}
	
	public static Befehlswort getBefehlswort(String text) {
		for(Befehlswort befehlswort : Befehlswort.values()) {
			if(text.equals(befehlswort.befehl)) {
				return befehlswort;
			}
		}
		return UNBEKANNT;
	}

	/**
	 * Pruefe, ob eine gegebene Zeichenkette ein gueltiger
	 * Befehl ist.
	 * 
	 * @return 'true', wenn die gegebene Zeichenkette ein gueltiger
	 *         Befehl ist, 'false' sonst.
	 */
	public static boolean istBefehl(String eingabe)
	{
		for(Befehlswort befehlswort : Befehlswort.values()) {
			if(eingabe.equals(befehlswort.befehl)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Gibt die akzeptierten Befehlsworte als Text zurueck.
	 * 
	 * @return Die akzeptierten Befehlsworte als Text
	 */
	public static String gibBefehlsworteAlsText() {
		String text = "";
		for (Befehlswort befehl : Befehlswort.values()) {
			text = text + befehl + " ";
		}
		System.out.println();
		return text;
	}
}
