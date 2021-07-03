package ch.zhaw.ads;

import java.util.Comparator;

/**
 * ADS FS2019
 * Praktikum 3
 * Aufgabe 3 – Rangliste
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class CompetitorComparator implements Comparator<Competitor_0> {

    @Override
    public int compare(Competitor_0 o1, Competitor_0 o2) {
        int nameCompare = o1.getName().compareTo(o2.getName());
        if (nameCompare < 0 || nameCompare > 0) return nameCompare;

        if (o1.getJg() < o2.getJg()) return -1;
        else if (o1.getJg() > o2.getJg()) return 1;
        else return 0;
    }
}
