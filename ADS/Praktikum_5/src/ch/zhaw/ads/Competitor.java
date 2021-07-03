package ch.zhaw.ads;

import java.text.*;
import java.util.*;

/**
 * ADS FS2019
 * 
 * Praktikum 5 
 * Aufgabe 3 – Rangliste mit binärem Suchbaum
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public final class Competitor implements Comparable<Competitor> {

	private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.S");
	private int number;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private String city;
	private Date time;

	public Competitor(int number, String firstName, String lastName, int yearOfBirth, String city, String timeString)
			throws NullPointerException, ParseException {

		if (firstName == null)
			throw new NullPointerException("Parameter \"firstName\" is null.");
		if (lastName == null)
			throw new NullPointerException("Parameter \"lastName\" is null.");
		if (city == null)
			throw new NullPointerException("Parameter \"city\" is null.");

		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.city = city;
		this.time = TIME_FORMAT.parse(timeString);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competitor other = (Competitor) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TIME_FORMAT == null) ? 0 : TIME_FORMAT.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + number;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + yearOfBirth;
		return result;
	}

	@Override
	public String toString() {
		return "Competitor [TIME_FORMAT=" + TIME_FORMAT + ", number=" + number + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", yearOfBirth=" + yearOfBirth + ", city=" + city + ", time=" + time + "]";
	}

	@Override
	public int compareTo(Competitor competitor) {
		int firstNameComparison = firstName.compareTo(competitor.getFirstName());
		int lastNameComparison = lastName.compareTo(competitor.getLastName());
		
		if(firstNameComparison == 0 && lastNameComparison == 0)
			return 0;
		if(firstNameComparison == 0)
			return lastNameComparison;
		return firstNameComparison;
	}

	public int getNumber() {
		return number;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public String getCity() {
		return city;
	}

	public Date getTime() {
		return time;
	}

	public String returnTimeString() {
		return TIME_FORMAT.format(time);
	}
}
