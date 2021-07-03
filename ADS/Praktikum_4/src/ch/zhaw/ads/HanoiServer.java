package ch.zhaw.ads;

import ch.zhaw.ads.CommandExecutor;

/**
 * ADS FS2019 
 * 
 * Praktikum 4 Aufgabe 1 – Türme von Hanoi
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class HanoiServer implements CommandExecutor {

    public void moveDisk(int numOfDisks, String from, String to, String help) {
        if (numOfDisks > 0) {
            // bewege Stapel n-1 von from auf help
            moveDisk(numOfDisks -1,from,help,to);
            // bewege von from nach to
            System.out.println("move " + from + " to " + to);
            // bewege Stapel n-1 von help auf to
            moveDisk(numOfDisks -1,help,to,from);
        }
    }

    @Override
    public String execute(String numberOfDisks) throws Exception {
        moveDisk(Integer.valueOf(numberOfDisks),"A","B","C");
        return "Done";
    }
}
