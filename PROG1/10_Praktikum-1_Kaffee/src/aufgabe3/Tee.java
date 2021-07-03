package aufgabe3;

/**
 * Diese Klasse bietet die Funktionalitaet, um einen Tee
 * zu kochen.
 * 
 * @author tebe
 */
public class Tee extends KoffeinGetraenk {

  /**
   * Bereitet einen Tee zu.
   */
  public void bereiteZu() {
    kocheWasser();
    taucheTeebeutel();
    giesseInTasse();
    fuegeZitroneHinzu();
  }
  
  @Override
  protected void kocheWasser() {
	  System.out.println("Wasser für den Tee wird gekocht.");
  }
  
  private void taucheTeebeutel() {
	  System.out.println("Teebeutel wird getaucht.");
  }
  
  @Override
  protected void giesseInTasse() {
	  System.out.println("Der Tee Tasse wird gegossen.");
  }
  
  private void fuegeZitroneHinzu() {
	  System.out.println("Zitrone wird hinzugefügt.");
  }
}
