package aufgabe1;
/**
 * Diese Klasse bietet die Funktionalitaet, um einen Kaffee
 * zu kochen.
 * 
 * @author tebe
 */
public class Kaffee {

  /**
   * Bereitet einen Kaffee zu.
   */
  public void bereiteZu() {
    kocheWasser();
    braueFilterKaffee();
    giesseInTasse();
    fuegeZuckerUndMilchHinzu();
  }

  private void kocheWasser() {
	  System.out.println("Wasser wird gekocht.");
  }
  
  private void braueFilterKaffee() {
	  System.out.println("Kaffe Filter wird gebraut.");
  }
  
  private void giesseInTasse() {
	  System.out.println("In Tasse wird gegossen.");
  }
  
  private void fuegeZuckerUndMilchHinzu() {
	  System.out.println("Zucker und Milch wird hinzugefügt.");
  }
}
