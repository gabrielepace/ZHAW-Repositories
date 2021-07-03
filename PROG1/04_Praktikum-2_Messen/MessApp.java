/**
 * Die Klasse MessApp steuert einen Messablauf, um die Performance des
 * Zufallszahlengenerators zu messen.
 */
public class MessApp {
  private Messkonduktor messkonduktor;
  private int[][] messungsResultat;

  /**
   * Fuehrt eine Messung durch.
   */
  public void messen() {
    initialisieren();
    analyseDurchfuehren();
    berechneUndDruckeMittelwerteMessreihe();
    berechneUndDruckeMittelwerteMessung();
  }

  private void initialisieren() {
    // TODO Objektsammlung und Messkonduktor erzeugen
     messungsResultat = new int[10][20];
     messkonduktor = new Messkonduktor(4000000);
  }

  private void analyseDurchfuehren() {
    // TODO Benutzen Sie 'messkonduktor' um die Messungen
    // durchzufuehren und in der Objektsammlung zu speichern.
    for(int zeile = 0 ; zeile < 10; zeile++){
        messkonduktor.messungenDurchfuehren(messungsResultat[zeile]);
    }
  }

  private void berechneUndDruckeMittelwerteMessreihe() {
    // TODO Implementieren Sie die Methode.
    for(int zeile = 0; zeile < 10; zeile++){
        int mittelwert = 0;
        for(int spalte = 0; spalte < 20; spalte++){
            mittelwert += messungsResultat[zeile][spalte];
        }
        System.out.println("Mittelwert Messreihe: " + (mittelwert / 20) + " sec.");
    }
  }

  private void berechneUndDruckeMittelwerteMessung() {
    // TODO Implementieren Sie die Methode.
    for(int zeile = 0; zeile < 20; zeile++){
        int mittelwert = 0;
        for(int spalte = 0; spalte < 10; spalte++){
            mittelwert += messungsResultat[spalte][zeile];
        }
        System.out.println("Mittelwert Messung: " + (mittelwert / 10) + " sec.");
    }
  }
}