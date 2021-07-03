package aufgabe3;

/**
 * Diese Klasse bietet die Funktionalitaet, um einen Kaffee
 * zu kochen.
 * 
 * @author tebe
 */
public class Kaffee extends KoffeinGetraenk {

  /**
   * Bereitet einen Kaffee zu.
   */
  public void bereiteZu() {
    kocheWasser();
    braueFilterKaffee();
    giesseInTasse();
    fuegeZuckerUndMilchHinzu();
  }
  
  @Override
  protected void kocheWasser() {
	  System.out.println("Wasser für Kaffee wird gekocht.");
  }
  
  private void braueFilterKaffee() {
	  System.out.println("Kaffee Filter wird gebraut.");
  }
  
  @Override
  protected void giesseInTasse() {
	  System.out.println("Kaffee Tasse wird gegossen.");
  }
  
  private void fuegeZuckerUndMilchHinzu() {
	  System.out.println("Zucker und Milch wird hinzugefügt.");
  }
}
