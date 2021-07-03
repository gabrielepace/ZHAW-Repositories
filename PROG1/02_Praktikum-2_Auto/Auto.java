
/**
 * Beschreiben Sie hier die Klasse Auto.
 * 
 * @author Gabriele Pace (pacegab1@students.zhaw.ch) 
 * @version 24.09.2018
 */
public class Auto
{
    private String marke;
    private String typ;
    private double hubraum;
    private String motor;
    private int lagerbestand;

    /**
     * Konstruktor für Objekte der Klasse Auto
     * 
     * Falls Auto mit Turbo --> "turbo" eintragen!
     * Falls Auto ohne Turbo --> "ohne turbo" eintragen!
     */
    public Auto(int lagerbestand, String marke, String typ, double hubraum, String motor){
        this.motor = motor;
        
        if((marke.length() >= 3 && marke.length() <= 10) || (typ.length() >= 3 && typ.length() <= 10)){
            this.marke = marke;
            this.typ = typ;
        }else{
            marke = "___";
            typ = "___";
            
            System.out.println("Es wurde ein falscher Wert von Automarke und Autotyp geschrieben!");
        }
        
        if(hubraum >= 0.5 && hubraum <= 8.0){
            this.hubraum = hubraum;
        }else{
            hubraum = 0;
            
            System.out.println("Es wurde ein falscher Hubraum Wert geschrieben!");
        }
        
        if(lagerbestand <= 10 && lagerbestand >= 0){
            this.lagerbestand = lagerbestand;
        }else{
            lagerbestand = 0;
            System.out.println("Es besteht im Lager kein Platz mehr zur Verfügung. Lagerbestand wurde 0 gestellt!");
            System.out.println("Bitte Lagerbestand ändern! Es darf höchsten bis 10 eingesetzt werden.");
        }
    }
    
    public void setMarke(String marke){
        this.marke = marke;
        
        if(marke == typ || marke == null){
            System.out.println("Es wurde ein falscher Wert eingetragen!");
        }
    }
    
    public void setTyp(String typ){
        this.typ = typ;
        
        if(typ == marke || typ == null){
            System.out.println("Es wurde ein falscher Wert eingetragen!");
        }
    }
    
    public void setHubraum(double hubraum){
        this.hubraum = hubraum;
        
        if(hubraum == 0){
            System.out.println("Es wurde ein falscher Wert eingetragen!");
        }
    }
    
    public void setMotor(String motor){
        this.motor = motor;
        
        if(motor == marke || motor == typ || motor == null){
            System.out.println("Es wurde ein falscher Wert eingetragen!");
        }
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int changeLagerbestand(int lagerbestand){       
        if(lagerbestand <= 10 && lagerbestand >= 0){
            this.lagerbestand = lagerbestand;
        }else{
            System.out.println("Es besteht im Lager kein Platz mehr zur Verfügung.");
        }
        return lagerbestand;
    }
    
    public void ausgeben()
    {
        if(motor == "turbo"){
        System.out.println(marke + " " + typ + ", " + hubraum + " Liter");
        System.out.println("Code: " + marke.substring(0,3) + "-" + typ.substring(0,3) + "-" + hubraum + "-" + motor.substring(0,1));
        System.out.println("Lagerbestand: " + lagerbestand);
        }else{
            System.out.println(marke + " " + typ + ", " + hubraum + " Liter");
            System.out.println("Code: " + marke.substring(0,3) + "-" + typ.substring(0,3) + "-" + hubraum);
            System.out.println("Lagerbestand: " + lagerbestand);
        }     
    }
}
