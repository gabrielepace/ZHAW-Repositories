package aufgabe1;

import java.util.List;

/**
 * Diese Klasse implementiert einen Briefdrucker
 * 
 * @author tebe
 */
public class Briefdrucker {

	private BriefdruckStrategie druckStrategie;

	public Briefdrucker(BriefdruckStrategie druckStrategie) {
		this.druckStrategie = druckStrategie;
	}

	public void setDruckStrategie(BriefdruckStrategie strategie) {
		this.druckStrategie = strategie;
	}

	public void druckeBrief(Adresse sender, Adresse empfaenger, Inhalt inhalt) {

		Brief brief = new Brief(sender, empfaenger, inhalt);
		druckStrategie.druckeBrief(brief);
	}

	public void druckeSerienbrief(Adresse sender, List<Adresse> listeEmpfaenger, Inhalt inhalt) {

		for (Adresse empfaenger : listeEmpfaenger) {

			Brief brief = new Brief(sender, empfaenger, inhalt);
			druckStrategie.druckeBrief(brief);
		}
	}
}
