
/**
 * Klasse Kuenstler - Praktikum 3 Aufgabe 3
 * 
 * @author Gabriele Pace (pacegab1@students.zhaw.ch)
 * @version 06.10.2018
 */
public class Kuenstler{
    private String kuenstler;
    private int gage;
    
    public Kuenstler(String kuenstler, int gage){
        this.kuenstler = kuenstler;
        this.gage = gage;
    }
    
    public void setKuenstler(String kuenstler){
        this.kuenstler = kuenstler;
    }
    
    public void setGage(int gage){
        this.gage = gage;
    }

    public String getKuenstler(){
        return kuenstler;
    }
    
    public int getGage(){
        return gage;
    }
}