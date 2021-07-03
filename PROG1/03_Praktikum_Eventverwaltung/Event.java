/**
 * Klasse Event - Praktikum 3 Aufgabe 3
 * 
 * @author Gabriele Pace (pacegab1@students.zhaw.ch) 
 * @version 06.10.2018
 */
public class Event{
    private Kuenstler kuenstler;
    private Ticket vip;
    private Ticket tribuene;
    private Ticket innenraum;
    
    public Event(){
      //Dieser Konstruktor tut nichts!  
    }
    
    public Event(Kuenstler kuenstler, Ticket vip, Ticket tribuene, Ticket innenraum){
        this.kuenstler = kuenstler;
        this.vip = vip;
        this.tribuene = tribuene;
        this.innenraum = innenraum;
    }
    
    public void ticketKaufen(int ticketBezeichnung, int anzahlTickets){
        Ticket bezeichnung = null;
        
        switch(ticketBezeichnung){
            case 1: bezeichnung = vip;
                    break;
            case 2: bezeichnung = tribuene;
                    break;
            case 3: bezeichnung = innenraum;
                    break;
            default: System.out.println("Die gegebene Ticketkategorie ist ungueltig!");
        }
        int aktuelleGekauftenTickets = bezeichnung.getGekauftenTickets();
        
        if((aktuelleGekauftenTickets + anzahlTickets) <= bezeichnung.getAnzahlTickets()){
            bezeichnung.setGekauftenTickets(aktuelleGekauftenTickets + anzahlTickets);
        }else{
            System.out.println("Es gibt kein verfuegbare Tickets!");
        }
    }
    
    public void ausgabe(){
        int einnahme1 = vip.getGekauftenTickets() * vip.getPreis();
        int einnahme2 = tribuene.getGekauftenTickets() * tribuene.getPreis();
        int einnahme3 = innenraum.getGekauftenTickets() * innenraum.getPreis();
        
        int gesamtEinnahme = einnahme1 + einnahme2 + einnahme3;
        
        System.out.println("Kuenstler: " + kuenstler.getKuenstler() + ", Gage: CHF " + kuenstler.getGage());
        System.out.println("VIP-Tickets: " + vip.getAnzahlTickets() + " von " + vip.getGekauftenTickets() + " verkauft, Einnahmen: CHF " + einnahme1);
        System.out.println("Tribuene-Tickets: " + tribuene.getAnzahlTickets() + " von " + tribuene.getGekauftenTickets() + " verkauft, Einnahmen: CHF " + einnahme2);
        System.out.println("Innenraum-Tickets: " + innenraum.getAnzahlTickets() + " von " + innenraum.getGekauftenTickets() + " verkauft, Einnahmen: CHF " + einnahme3);
        System.out.println("Gesamteinnahmen: CHF " + gesamtEinnahme);
        System.out.println("Gewinn: CHF " + (gesamtEinnahme - (kuenstler.getGage())));
    }
}