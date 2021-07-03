package aufgabe2;

import java.text.SimpleDateFormat;

public class StandardBriefFormat implements BriefFormatierungStrategie {

	StringBuilder builder = new StringBuilder();

	@Override
	public StringBuilder formatiereBrief(Brief brief) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		String datum = dateFormat.format(brief.getInhalt().getDatum().getTime());
		builder.append(datum + "\n");
		builder.append(adresseFormatieren(brief.getSender()) + "\n");
		builder.append(adresseFormatieren(brief.getEmpfaenger()) + "\n");
		builder.append(brief.getInhalt().getTitel());
		builder.append("Sehr geehrte/r " + brief.getInhalt().getAnrede() + brief.getEmpfaenger().getNachname());
		builder.append(brief.getInhalt().getText());
		return null;
	}

	private String adresseFormatieren(Adresse adresse) {

		return adresse.getVorname() + " " + adresse.getNachname() + "\n" + adresse.getStrasse() + " "
				+ adresse.getHausnummer() + "\n" + adresse.getPlz() + " " + adresse.getOrt();
	}
}
