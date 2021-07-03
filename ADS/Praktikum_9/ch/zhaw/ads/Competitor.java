package ch.zhaw.ads;

import java.util.*;
import java.text.*;

/**
 * ADS FS2019 
 * 
 * Praktikum 8 Aufgabe 2 – Competitor Methoden
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class Competitor implements Comparable<Competitor> {
	private String name;
	private String country;
	private long time;
	private int jg;
	private int startNr;
	private int rank;

	public Competitor(int startNr, String name, int jg, String country, String time) throws ParseException {
		this.startNr = startNr;
		this.name = name;
		this.jg = jg;
		this.country = country;
		this.time = Competitor.parseTime(time);
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
		sb.append(rank);
		sb.append(" ");
		sb.append(name);
		sb.append(" ");
		sb.append(Integer.toString(jg));
		sb.append(" ");
		sb.append(df.format(new Date(time)));
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return this.jg * this.name.hashCode() * 3;
	}

	@Override
	public int compareTo(Competitor o) {
		int valueCompare = this.getName().compareTo(o.getName());
		if (valueCompare == 0) {
			return ((Integer) this.getJg()).compareTo(o.getJg());
		}
		return valueCompare;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Competitor) {
			return ((Competitor) obj).name.equals(this.name) && ((Competitor) obj).jg == this.jg;
		}
		return false;
	}
}