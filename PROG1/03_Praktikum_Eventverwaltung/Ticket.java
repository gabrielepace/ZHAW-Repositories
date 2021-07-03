/**
 * Klasse Ticket - Praktikum 3 Aufgabe 3
 * 
 * @author Gabriele Pace (pacegab1@students.zhaw.ch)
 * @version 06.10.2018
 */
public class Ticket{
    private String bezeichnung;
    private int preis;
    private int anzahlTickets;
    private int gekauftenTickets = 0;
    
    public Ticket(String bezeichnung, int ticketPreis,int anzahlTickets){
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.anzahlTickets = anzahlTickets;
    }
    
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }
    
    public String getBezeichnung(){
        return bezeichnung;
    }
    
    public void setPreis(int preis){
        this.preis = preis;
    }
    
    public int getPreis(){
        return preis;
    }
    
    public void setAnzahlTickets(int anzahlTickets){
        this.anzahlTickets = anzahlTickets;
    }
    
    public int getAnzahlTickets(){
        return anzahlTickets;
    }
    
    public void setGekauftenTickets(int gekauftenTickets){
        this.gekauftenTickets = gekauftenTickets;
    }
    
    public int getGekauftenTickets(){
        return gekauftenTickets;
    }
}
