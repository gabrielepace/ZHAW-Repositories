/**
 * Praktikum 2 - Aufgabe 4 (optional)
 * 
 * @author Gabriele Pace (pacegab1@students.zhaw.ch)
 * @version 30.09.2018
 */
public class Konto{
    private String kontoinhaber;
    private int kontostand;
    private int zinssatz;

    /**
     * Konstruktor für Objekte der Klasse Konto
     */
    public Konto(String kontoinhaber, int zinssatz){        
      if((kontoinhaber.length() >= 3 && kontoinhaber.length() <= 10) && (zinssatz >= 0 && zinssatz <= 10)){ //Kontoinhaber muss mind. 3 bis 10 Wörten Lang sein, sowie auch den Zinssatz zwischen 0 und 10!
          this.kontoinhaber = kontoinhaber;
          this.zinssatz = zinssatz;
      }else if(!(kontoinhaber.length() >= 3 && kontoinhaber.length() <= 10)){
        this.kontoinhaber = "no name";
        System.err.println("Name der Kontoinhaber ist entweder zu kurz oder zu lang! Konto wurde als \"no name\" gesetzt.");
        
        if(zinssatz >= 0 && zinssatz <= 10){
              this.zinssatz = zinssatz;
          }else{
              this.zinssatz = 0;
              System.err.println("Der Zinssatz muss mind. 0 bis 10 sein! Der Zinssatz wird auf 0 gesetzt.");
          }
       }else if(kontoinhaber.length() >= 3 && kontoinhaber.length() <= 10){
        this.kontoinhaber = kontoinhaber;
        
        if(zinssatz >= 0 && zinssatz <= 10){
              this.zinssatz = zinssatz;
          }else{
              this.zinssatz = 0;
              System.err.println("Der Zinssatz muss mind. 0 bis 10 sein! Der Zinssatz wird auf 0 gesetzt.");
          }
        }
    }
    
    /**
     * Hier kann man den Zinssatz ändern.
     */
    public int changeZinssatz(int zinssatz){ //Der Zinssatz muss immer zwischen 0 und 10 sein!
        
        if(zinssatz >= 0 && zinssatz <= 10){
            this.zinssatz = zinssatz;
        }else{
            System.out.println("Der Zinssatz muss mind. 0 bis 10 sein! Der Zinssatz wird nicht geändert.");
        }
        return zinssatz;
    }
        
    public int getZinssatz(){
        return zinssatz;
    }
        
    /**
     * Geld wird im Kontostand einbezahlt.
     */
    public int geldEinzahlen(int geld){
        if(geld >= 0 && geld <= 10000){
            kontostand += geld;
        }else{
            System.err.println("Man kann nur bis CHF 10'000 einbezahlen!");
        }       
        return kontostand;
    }
    
    /**
     * Geld. wird hier abgehoben vom eigene Konto.
     */
    public int betragAbheben(int betrag){
        if(betrag > 10000){
            System.err.println("Man kann nur bis CHF 10'000 abheben!");
        }else if(betrag > kontostand){
            System.err.println("Man kann nicht " + betrag + " abheben, als es sich nicht zur Verfügung hat!!!");
        }else if(betrag >= 0 && betrag <= 10000){
            kontostand -= betrag;
        }
        return kontostand;
    }
    
    /**
     * Wird der Jahreszins berechnet, basiert auf dem gegebenen Zinssatz beim Konto erzeugen oder im @changeZinssatz() Methode, falls der Zinssatz wurde geändert.
     */
    public int jahreszinsBerechnen(){
        int jahreszins = 0;
        jahreszins = (kontostand * zinssatz) / 100;  //Formel wäre Z = (K * p) / 100 aus https://www.formelsammlung-mathe.de/zinsrechnung.html
        return jahreszins;
        
    }
    
    /**
     * Werden die benötigen Infos auf der Kommandozeile ausgedrückt.
     */
    public void printOutput(){
        System.out.println("Informationen zum Konto: ");
        System.out.println("Kontoinhaber: " + kontoinhaber);
        System.out.println("Kontostand: CHF " + kontostand);
        System.out.println("Zinssatz: " + zinssatz + "%");
    }  
}
