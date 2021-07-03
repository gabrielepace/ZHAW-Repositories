package aufgabe1;
/**
 * Diese Klasse bietet die Funktionalitaet, um einen Tee
 * zu kochen.
 * 
 * @author tebe
 */
public class Tee {

  /**
   * Bereitet einen Tee zu.
   */
  public void bereiteZu() {
    kocheWasser();
    taucheTeebeutel();
    giesseInTasse();
    fuegeZitroneHinzu();
  }

  private void kocheWasser() {
	  System.out.println("Wasser wird gekocht.");
  }
  
  private void taucheTeebeutel() {
	  System.out.println("Teebeutel wird getaucht.");
  }
  
  private void giesseInTasse() {
	  System.out.println("In Tasse wird gegossen.");
  }
  
  private void fuegeZitroneHinzu() {
	  System.out.println("Zitrone wird hinzugefügt.");
  }
}
