/**
 * Diese Klasse verarbeitet Befehle vom Typ {@link Befehl Befehl} und lÃ¶sst die
 * dazu passenden Aktionen aus.
 * 
 * @author tebe
 *
 */
public class Kontroller {
	/**
	 * Verarbeite einen gegebenen Befehl
	 * 
	 * @param befehl Der zu verarbeitende Befehl.
	 * @return 'false', wenn ein Abbruchbefehl verarbeitet wurde
	 */
	public boolean verarbeiteBefehl(Befehl befehl) {
		boolean macheWeiter = true;
		Befehlswort befehlswort = befehl.gibBefehlswort();
		
		if (Befehlswort.GEHE.equals(befehlswort)) {
			System.out.println("Befehl GEHE " + befehl.gibZweitesWort() + " wird ausgefuehrt");
		} else if (Befehlswort.HILFE.equals(befehlswort)) {
			System.out.println("Gueltige Befehle: " + Befehlswort.gibBefehlsworteAlsText() + "(klein geschrieben)");
		} else if (Befehlswort.BEENDEN.equals(befehlswort)) {
			System.out.println("Befehl BEENDEN wird ausgefuehrt.");
			macheWeiter = false;
		} else {
			System.out.println("Ich weiss nicht, was Sie meinen...");
			System.out.println("Befehlswort ohne zugehoerige Aktion: Schreiben Sie ein gültiges Befehl.");
			System.out.println("Falls Sie den Befehl nicht kennen, dann schreiben Sie \"hilfe\" ohne Anführungszeichen.");
			macheWeiter = true;
		}
		return macheWeiter;
	}
}
