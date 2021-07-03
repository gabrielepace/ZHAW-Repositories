package ch.zhaw.ads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Comparable;
import java.util.*;

/**
 * ADS FS2019
 * Praktikum 3
 * Aufgabe 2 – RankingServer
 * Aufgabe 4 – Namensliste
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class RankingServer implements CommandExecutor {
    private String input;
    String[] split;
    List<Competitor_0> list = new ArrayList<Competitor_0>();
    
    @Override
    public String execute(String command) throws Exception{
        
        list = createCompetitorList(command);
        setRank();
        StringBuffer result = new StringBuffer(9000);
        result.append("Folgende Sportler wurden hinzugefügt: \n");
        for(Competitor_0 champ : list){
            result.append("Rank: " + champ.getRank() + " " + champ.getName() + "\n");
        }
        return (result.toString());
    }
    
    @SuppressWarnings("unchecked")
	private List createCompetitorList(String input) throws Exception{
        List tempList = new ArrayList();
        for (String line : input.split("\\r?\\n")){
            String[] compet = line.split(";");
            if (compet.length < 5){
                throw new IllegalArgumentException("Input is incorrect!");            
            }   
            else{
                tempList.add(new Competitor_0(Integer.parseInt(compet[0]),compet[1], Integer.parseInt(compet[2]),compet[3],compet[4]));
            }
        }
        return tempList;
    }
    
    public void setRank(){
        Collections.sort(this.list);
        int rank = 1;
        for(Competitor_0 champ : this.list){
            champ.setRank(rank);
            rank++;
        }
    }
}