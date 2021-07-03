package aufgabe1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Diese Klasse implementiert ein Briefprogramm.
 * 
 * @author tebe
 */
public class Briefprogramm {

	/**
	 * main Methode.
	 * 
	 * @param args Es werden keine Kommandozeilenparameter verwendet
	 */

	public static void main(String[] args) {
		
		Inhalt inhalt = new Inhalt(Calendar.getInstance(), "Test Titel", "Herr/Frau Muster",
				"TextTextTextTextTextTextTextText");
		Adresse adresseSender = new Adresse("VornameSender", "NameSender", "Senderstrasse", 2, 8001,
				"MusterortSender");
		Adresse adresseEmpfaenger = new Adresse("VornameEmpfänger", "NameEmpfänger", "Empfängerstrasse", 1, 8000,
				"MusterortEmpfänger");

		BriefdruckStrategie fensterBrief = new FensterBriefdruckStrategie();
		BriefdruckStrategie standardBrief = new StandardBriefdruckStrategie();

		Briefdrucker standardBriefdruck = new Briefdrucker(fensterBrief);
		Briefdrucker fensterBriefdruck = new Briefdrucker(standardBrief);

		// Liste Empfänger for Serienbrief
		List<Adresse> empfaengerList = new ArrayList<>();
		empfaengerList.add(new Adresse("VornameEmpfänger1", "NameEmpfänger1", "Empfängerstrasse", 1, 8001,
				"MusterortEmpfänger"));
		empfaengerList.add(new Adresse("VornameEmpfänger2", "NameEmpfänger2", "Empfängerstrasse", 2, 8002,
				"MusterortEmpfänger"));
		empfaengerList.add(new Adresse("VornameEmpfänger3", "NameEmpfänger3", "Empfängerstrasse", 3, 8003,
				"MusterortEmpfänger"));

		// Print outs:
		System.out.println("Standard Brief print out:\n");
		standardBriefdruck.druckeBrief(adresseSender, adresseEmpfaenger, inhalt);
		System.out.println("\n----------------------------------\n----------------------------------\n");
		System.out.println("Fenster Brief print out:\n");
		fensterBriefdruck.druckeBrief(adresseSender, adresseEmpfaenger, inhalt);
		System.out.println("\n----------------------------------\n----------------------------------\n");
		System.out.println("----------------------------------\n----------------------------------\n");
		System.out.println("Standard Serienbrief print out:\n");
		standardBriefdruck.druckeSerienbrief(adresseSender, empfaengerList, inhalt);

	}
}