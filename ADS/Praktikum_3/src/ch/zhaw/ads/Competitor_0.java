package ch.zhaw.ads;

import java.util.*;
import java.text.*;

/**
 * ADS FS2019
 * Praktikum 3
 * Aufgabe 1 – Competitor
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class Competitor_0 {
	private String name;
	private String country;
	private long time;
	private int jg;
	private int startNr;
	private int rank;

	public Competitor_0(int startNr, String name, int jg, String country, String time) {
		// TODO: implement
		this.startNr = startNr;
		this.name = name;
		this.jg = jg;
		this.country = country;
		try {
            this.time = parseTime(time);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public String getName() {
		return name;
	}
  
	public int getJg() {
		return jg;
	}
  
	private static long parseTime(String s) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
		Date date = sdf.parse(s);
		return date.getTime();
	}

	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
		StringBuilder sb = new StringBuilder();
		sb.append(rank);sb.append(" ");
		sb.append(name); sb.append(" ");
		sb.append(Integer.toString(jg)); sb.append(" ");
		sb.append(df.format(new Date(time)));
		return sb.toString();
	}

	public int getRank() {
		return rank;
	}
	
}
