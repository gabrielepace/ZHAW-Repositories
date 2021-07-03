package aufgabe1;

import java.text.SimpleDateFormat;

public class FensterBriefdruckStrategie implements BriefdruckStrategie {
	
	@Override
	public void druckeBrief(Brief brief) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String datum = dateFormat.format(brief.getInhalt().getDatum().getTime());
		
		System.out.println(datum + "\n");
		System.out.println(adresseFormatieren(brief.getSender()) + "\n");
		System.out.println(empfaengerFormatieren(brief.getEmpfaenger()) + "\n");
		System.out.println(brief.getInhalt().getTitel());
		System.out.println("Sehr geehrte/r " + brief.getInhalt().getAnrede() + brief.getEmpfaenger().getNachname());
		System.out.println(brief.getInhalt().getText());
	}

	private String adresseFormatieren(Adresse adresse) {

		return adresse.getVorname() + " " + adresse.getNachname() + 
			"\n" + adresse.getStrasse() + " " + adresse.getHausnummer() + 
			"\n" + adresse.getPlz() + " " + adresse.getOrt();
	}
	
	private String empfaengerFormatieren(Adresse adresse) {

		return "\t\t\t\t" + adresse.getVorname() + " " + adresse.getNachname() + 
			"\n\t\t\t\t" + adresse.getStrasse() + " " + adresse.getHausnummer() + 
			"\n\t\t\t\t" + adresse.getPlz() + " " + adresse.getOrt();
	}
}
