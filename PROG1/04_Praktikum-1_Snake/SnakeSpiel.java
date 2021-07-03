import java.awt.Point;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Spielklasse des Spiels Snake.
 *
 * Ziel dieses Spiels ist es alle Goldstuecke einzusammeln und 
 * die Tuer zu erreichen, ohne sich selber zu beissen oder in 
 * die Spielfeldbegrenzung reinzukriechen. 
 */
public class SnakeSpiel {
  private Schlange schlange;
  private Tuer tuer;
  private Spielfeld spielfeld;
  private Point goldStueck;
  private boolean spielLaeuft = true;
  private int anzahlGoldstuecke;
  private ArrayList<Point> goldStuecke = new ArrayList<Point>();

  /**
   * Startet das Spiel.
   */
  public void spielen() {
    spielInitialisieren();
    while (spielLaeuft) {
      zeichneSpielfeld();
      ueberpruefeSpielstatus();
      fuehreSpielzugAus();
    }   
  }
  
  public static void main(String[] args) {
    new SnakeSpiel().spielen();
  }

  private void spielInitialisieren() {
    tuer = new Tuer(0, 5);
    spielfeld = new Spielfeld(40, 10);
    goldStueck = new Point(20, 2);
    schlange = new Schlange(30, 2);
    
  }

  private void zeichneSpielfeld() {
    char ausgabeZeichen;
    for (int y = 0; y < spielfeld.gibHoehe(); y++) {
      for (int x = 0; x < spielfeld.gibBreite(); x++) {
        Point punkt = new Point(x, y);
        ausgabeZeichen = '.';
        if (schlange.istAufPunkt(punkt)) {
          ausgabeZeichen = '@';
        } else if (istEinGoldstueckAufPunkt(punkt)) {
          ausgabeZeichen = '$';
        } else if (tuer.istAufPunkt(punkt)) {
          ausgabeZeichen = '#';
        } 
        if(schlange.istKopfAufPunkt(punkt)) {
          ausgabeZeichen = 'S';         
        }
        System.out.print(ausgabeZeichen);
      }
      System.out.println();
    }
  }
  
  private void setzteGoldstuecke(){
      for(int i = 0; i < anzahlGoldstuecke; i++){
          goldStuecke.add(spielfeld.erzeugeZufallspunktInnerhalb());
      }
  }
  
  private void setAnzahlGoldstuecke(int anzahlGoldstuecke){
          this.anzahlGoldstuecke = anzahlGoldstuecke;
  }
  
  private boolean istEinGoldstueckAufPunkt(Point punkt) {
    return goldStueck != null && goldStueck.equals(punkt);
  }
  
  private void ueberpruefeSpielstatus() {
    if (istEinGoldstueckAufPunkt(schlange.gibPosition())) {
      goldStuecke.remove(schlange.gibPosition());
      int punkte = (int)(Math.random() * 5) + 1;
      schlange.wachsen(punkte);
      System.out.println("Goldstueck eingesammelt. Es wurden " + punkte + " Punkte eingesammelt!");
    }
    if (istVerloren()){
      System.out.println("Verloren!");
      spielLaeuft = false;
    }
    if (istGewonnen()){
      System.out.println("Gewonnen!");
      spielLaeuft = false;
    }
  }
  
  private boolean istGewonnen() {
    return goldStueck == null && 
      tuer.istAufPunkt(schlange.gibPosition());
  }

  private boolean istVerloren() {
    return schlange.istKopfAufKoerper() || 
         !spielfeld.istPunktInSpielfeld(schlange.gibPosition());
  }
  
  private void fuehreSpielzugAus() {
    char eingabe = liesZeichenVonTastatur();
    schlange.bewege(eingabe);
  }

  private char liesZeichenVonTastatur() {
    Scanner scanner = new Scanner(System.in);
    char konsolenEingabe = scanner.next().charAt(0);
    return konsolenEingabe;
  }
}